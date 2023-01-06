package br.com.pb.barbershop.msscheduling.aplication.service;

import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.aplication.ports.out.UserRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.domain.dto.PageableDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.enums.Status;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.exception.GenericException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService implements SchedulingUseCase {

    private final SchedulingRepositoryPortOut schedulingRepository;

    private final UserRepositoryPortOut userRepository;
    private final ModelMapper mapper;

    @Override
    public SchedulingDTO create(SchedulingDTO schedulingDTO) {
        checkIsCustomer(schedulingDTO.getCustomerId());
        checkIsEmployee(schedulingDTO.getBarberId());
        schedulingValidation(schedulingDTO);

        var scheduling = mapper.map(schedulingDTO, Scheduling.class);
        scheduling.setStatus(Status.AGUARDANDO_PAGAMENTO);

        schedulingRepository.save(scheduling);
        return mapper.map(scheduling, SchedulingDTO.class);
    }

    @Override
    public PageableDTO findAll(SchedulingFilter schedulingFilter, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher
            .matching()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Scheduling scheduling = Scheduling
            .builder()
            .customerId(schedulingFilter.getCustomerId())
            .barberId(schedulingFilter.getBarberId())
            .dateTime(schedulingFilter.getDateTime())
            .service(schedulingFilter.getService())
            .build();

        Example<Scheduling> example = Example.of(scheduling, exampleMatcher);

        Page schedulingPage = schedulingRepository.findAll(example, pageable);

        List<Scheduling> schedulings = schedulingPage.getContent();

        return PageableDTO
            .builder()
            .numberOfElements(schedulingPage.getNumberOfElements())
            .totalElements(schedulingPage.getTotalElements())
            .totalPages(schedulingPage.getTotalPages())
            .schedulingList(schedulings)
            .build();
    }

    @Override
    public SchedulingDTO findById(Long id) {
        return mapper.map(getScheduling(id), SchedulingDTO.class);
    }

    @Override
    public SchedulingDTO update(Long id, SchedulingDTO request) {
        schedulingValidation(request);
        Optional<Scheduling> optional = schedulingRepository.findById(id);
        if (optional.isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Agendamento não existe.");
        }
        Scheduling scheduling = optional.get();
        scheduling.setDateTime(request.getDateTime() == null ? scheduling.getDateTime() : request.getDateTime());

        checkIsCustomer(scheduling.getCustomerId());
        checkIsEmployee(scheduling.getBarberId());

        schedulingRepository.save(scheduling);
        return mapper.map(scheduling, SchedulingDTO.class);
    }

    @Override
    public void delete(Long id) {
        checkIfIdExists(id);
        schedulingRepository.deleteById(id);
    }

    private void schedulingValidation(SchedulingDTO schedulingDTO) {
        if (LocalDateTime.now().isAfter(schedulingDTO.getDateTime())) {
            throw new GenericException(
                HttpStatus.BAD_REQUEST,
                "Não é permitido data retroativa, digite uma data correta!"
            );
        }

        var scheduledMinus = schedulingDTO.getDateTime().minus(30, ChronoUnit.MINUTES);
        var scheduledPlus = schedulingDTO.getDateTime().plus(30, ChronoUnit.MINUTES);
        var customerIsAlreadyScheduled = schedulingRepository.findByCustomerIdAndDateTimeLessThanAndDateTimeGreaterThan(
            schedulingDTO.getCustomerId(),
            scheduledPlus,
            scheduledMinus
        );

        if (customerIsAlreadyScheduled.isPresent()) {
            if (!customerIsAlreadyScheduled.get().getBarberId().equals(schedulingDTO.getBarberId())) {
                throw new GenericException(
                    HttpStatus.BAD_REQUEST,
                    "Este cliente possui horário marcado com o barbeiro " +
                    customerIsAlreadyScheduled.get().getBarberId()
                );
            } else if (customerIsAlreadyScheduled.get().getBarberId().equals(schedulingDTO.getBarberId())) {
                throw new GenericException(
                    HttpStatus.BAD_REQUEST,
                    "Este cliente já possui agendamento com barbeiro no mesmo horário!"
                );
            }
        }
        var barberIsAlreadyScheduled = schedulingRepository.findByBarberIdAndDateTimeLessThanAndDateTimeGreaterThan(
            schedulingDTO.getBarberId(),
            scheduledPlus,
            scheduledMinus
        );

        if (barberIsAlreadyScheduled.isPresent()) {
            throw new GenericException(
                HttpStatus.BAD_REQUEST,
                "Horário de agendamento não disponivel para este barbeiro."
            );
        }
    }

    private void checkIfIdExists(Long id) {
        schedulingRepository
            .findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "Id não encontrado!"));
    }

    private void checkIsCustomer(Long id) {
        if(id == null || userRepository.findByIdAndProfile(id, "ROLE_CUSTOMER").isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Cliente informado não existe!");
        }
    }

    private void checkIsEmployee(Long id) {
        if(id == null || userRepository.findByIdAndProfile(id, "ROLE_EMPLOYEE").isEmpty()) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Barbeiro informado não existe!");
        }
    }
    private Scheduling getScheduling(Long id) {
        Optional<Scheduling> scheduling = schedulingRepository.findById(id);
        return scheduling.orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "Id não encontrado!"));
    }
}
