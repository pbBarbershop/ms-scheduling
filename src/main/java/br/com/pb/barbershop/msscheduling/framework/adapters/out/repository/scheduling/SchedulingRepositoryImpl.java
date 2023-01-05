package br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.scheduling;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulingRepositoryImpl implements SchedulingRepositoryPortOut {

    private final SchedulingJpaRepository repository;

    @Override
    public Optional<Scheduling> findByDateTime(LocalDateTime dateTime) {
        return repository.findByDateTime(dateTime);
    }

    @Override
    public Optional<Scheduling> findByDateTimeAndBarberId(LocalDateTime dateTime, Long barberId) {
        return repository.findByDateTimeAndBarberId(dateTime, barberId);
    }

    @Override
    public Optional<Scheduling> findByCustomerIdAndDateTime(Long customerId, LocalDateTime dateTime) {
        return repository.findByCustomerIdAndDateTime(customerId, dateTime);
    }

    @Override
    public Page<Scheduling> findAll(Example<Scheduling> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    public Optional<Scheduling> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public Scheduling save(Scheduling entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Scheduling> findByCustomerIdAndDateTimeLessThanAndDateTimeGreaterThan(
        Long customerId,
        LocalDateTime dateTimePlus,
        LocalDateTime dateTimeMinus
    ) {
        return repository.findByCustomerIdAndDateTimeLessThanAndDateTimeGreaterThan(
            customerId,
            dateTimePlus,
            dateTimeMinus
        );
    }

    @Override
    public Optional<Scheduling> findByBarberIdAndDateTimeLessThanAndDateTimeGreaterThan(
        Long barberId,
        LocalDateTime dateTimePlus,
        LocalDateTime dateTimeMinus
    ) {
        return repository.findByBarberIdAndDateTimeLessThanAndDateTimeGreaterThan(
            barberId,
            dateTimePlus,
            dateTimeMinus
        );
    }

    @Override
    public Optional<Scheduling> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        }
    }
}
