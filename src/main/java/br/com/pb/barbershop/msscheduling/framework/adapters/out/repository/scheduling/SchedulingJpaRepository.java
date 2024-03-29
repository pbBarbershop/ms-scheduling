package br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.scheduling;

import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import java.time.LocalDateTime;
import java.util.Optional;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SchedulingJpaRepository extends JpaRepository<Scheduling, Long> {
    Optional<Scheduling> findByDateTime(LocalDateTime dateTime);

    Optional<Scheduling> findByDateTimeAndBarberId(LocalDateTime dateTime, Long barberId);

    Optional<Scheduling> findByCustomerIdAndDateTime(Long customerId, LocalDateTime dateTime);

    Page<Scheduling> findAll(Example example, Pageable pageable);

    Optional<Scheduling> findByCustomerId(Long customerId);

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
}