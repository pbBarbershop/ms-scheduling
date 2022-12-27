package br.com.pb.barbershop.msscheduling.aplication.service;

import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.exception.DataIntegrityValidationException;
import br.com.pb.barbershop.msscheduling.framework.exception.IdNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService implements SchedulingUseCase {

    private final SchedulingRepository schedulingRepository;

    private final ModelMapper mapper;

    @Override
    public Scheduling update(Long id, SchedulingDTO request) {
        this.checkIfIdExists(id);
        request.setId(id);
        this.findByEmail(request);
        Optional<Scheduling> optional = schedulingRepository.findById(id);
        Scheduling scheduling = optional.get();
        scheduling.setCustomerName(request.getCustomerName());
        scheduling.setCustomerPhoneNumber(request.getCustomerPhoneNumber());
        scheduling.setCustomerEmail(request.getCustomerEmail());
        scheduling.setDate(request.getDate());
        scheduling.setTime(request.getTime());
        schedulingRepository.save(scheduling);
        return mapper.map(scheduling, Scheduling.class);
    }

    private void checkIfIdExists(Long id) {
        schedulingRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    private void findByEmail(SchedulingDTO request) {
        Optional<Scheduling> scheduling = schedulingRepository.findByCustomerEmail(request.getCustomerEmail());
        if (scheduling.isPresent() && !scheduling.get().getId().equals(request.getId())) {
            throw new DataIntegrityValidationException("Email já está em uso.");
        }
    }
}
