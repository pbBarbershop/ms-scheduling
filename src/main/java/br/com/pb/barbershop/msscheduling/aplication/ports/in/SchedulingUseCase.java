package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.exception.IdNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingUseCase implements SchedulingService {

    private final SchedulingRepository repository;

    public Page<Scheduling> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable) {
        Scheduling scheduling = Scheduling.builder()
                .id(schedulingFilter.getId())
                .clientName(schedulingFilter.getClientName())
                .clientEmail(schedulingFilter.getClientEmail())
                .clientContact(schedulingFilter.getClientContact())
                .dateScheduling(schedulingFilter.getDateScheduling())
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

}


