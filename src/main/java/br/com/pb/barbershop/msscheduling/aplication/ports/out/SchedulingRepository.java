package br.com.pb.barbershop.msscheduling.aplication.ports.out;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    Optional<Scheduling> findByDate(LocalDate date);

    Optional<Scheduling> findByDateAndTimeAndBarberName(LocalDate date, LocalTime time, String barberName);

    Optional<Scheduling> findByCustomerEmailAndDateAndTime(String clientEmail, LocalDate date, LocalTime time);

    Page<Scheduling> findAll(Example example, Pageable pageable);

    Optional<Scheduling> findByCustomerName(String clientEmail);
}
