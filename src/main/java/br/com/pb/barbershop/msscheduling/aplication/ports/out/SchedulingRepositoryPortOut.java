package br.com.pb.barbershop.msscheduling.aplication.ports.out;

import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepositoryPortOut {
    Optional<Scheduling> findByDateTime(LocalDateTime dateTime);

    Optional<Scheduling> findByDateTimeAndBarberId(LocalDateTime dateTime, Long barberId);

    Optional<Scheduling> findByCustomerIdAndDateTime(Long customerId, LocalDateTime dateTime);

    Optional<Scheduling> findByCustomerId(Long customerId);
    Page findAll(Example<Scheduling> example, Pageable pageable);

    Scheduling save(Scheduling entity);

    Optional<Scheduling> findByCustomerIdAndDateTimeLessThanAndDateTimeGreaterThan(
        Long customerId,
        LocalDateTime dateTimePlus,
        LocalDateTime dateTimeMinus
    );
    Optional<Scheduling> findByBarberIdAndDateTimeLessThanAndDateTimeGreaterThan(
        Long barberId,
        LocalDateTime dateTimePlus,
        LocalDateTime dateTimeMinus
    );

    Optional<Scheduling> findById(Long id);

    void deleteById(Long id);
}
