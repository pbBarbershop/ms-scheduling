package br.com.pb.barbershop.msscheduling.aplication.ports.in;
import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingResponse;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.exception.DataIntegrityValidationException;
import br.com.pb.barbershop.msscheduling.framework.exception.IdNotFoundException;
import br.com.pb.barbershop.msscheduling.framework.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SchedulingUseCase implements SchedulingService {

    private final SchedulingRepository repository;
    private final ModelMapper mapper;

    @Override
    public SchedulingResponse createScheduling(SchedulingDTO schedulingDTO) {
        schedulingValidation(schedulingDTO);
        var scheduling = mapper.map(schedulingDTO, Scheduling.class);
        repository.save(scheduling);;
        return mapper.map(scheduling, SchedulingResponse.class);
    }

    private void schedulingValidation(SchedulingDTO schedulingDTO) {
        if (LocalDate.now().isAfter(schedulingDTO.getDate())){
            throw new DataIntegrityValidationException("Não é permitido data retroativa, digite uma data correta!");
        }
        var schedulingCheckOne = repository.findByClientEmailAndDateAndTime(schedulingDTO.getClientEmail(),
                schedulingDTO.getDate(), schedulingDTO.getTime());

        if(schedulingCheckOne.isPresent()){
            if(!schedulingCheckOne.get().getBarberName().equals(schedulingDTO.getBarberName())){
                throw new DataIntegrityValidationException("Este cliente possui horário marcado com o barbeiro "+schedulingCheckOne.get().getBarberName());
            }else if(schedulingCheckOne.get().getBarberName().equals(schedulingDTO.getBarberName())){
                throw new DataIntegrityValidationException("Este cliente já possui agendamento para este barbeiro no mesmo horário!");
            }
        }
        var schedulingCheckTwo = repository.findByDateAndTimeAndBarberName(schedulingDTO.getDate()
                , schedulingDTO.getTime(), schedulingDTO.getBarberName());

        if(schedulingCheckTwo.isPresent()){
            throw new DataIntegrityValidationException("horário de agendamento não disponivel para este barbeiro.");
        }

    }
    @Override
    public Page<Scheduling> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable) {
        Scheduling scheduling = Scheduling.builder()
                .clientName(schedulingFilter.getClientName())
                .clientEmail(schedulingFilter.getClientEmail())
                .clientPhone(schedulingFilter.getClientPhone())
                .date(schedulingFilter.getDate())
                .time(schedulingFilter.getTime())
                .service(schedulingFilter.getService())
                .barberName(schedulingFilter.getBarberName())
                .build();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Scheduling> example = Example.of(scheduling, exampleMatcher);

        return repository.findAll(example, pageable);
    }

    private void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        checkIfIdExists(id);
        repository.deleteById(id);
    }
    @Override
    public SchedulingDTO findById(Long id){
        return mapper.map(getScheduling(id), SchedulingDTO.class);
    }

    public Scheduling getScheduling(Long id){
        Optional<Scheduling> scheduling = repository.findById(id);
        return scheduling.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado!"));
    }

}


