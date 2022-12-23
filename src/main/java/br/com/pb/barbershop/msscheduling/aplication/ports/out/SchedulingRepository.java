package br.com.pb.barbershop.msscheduling.aplication.ports.out;

import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    Optional<Scheduling> findByDate(LocalDate date);
    Optional<Scheduling>findByDateAndTimeAndBarberName(LocalDate date, LocalTime time, String barberName);

    Optional<Scheduling> findByClientEmailAndDateAndTime(String clientEmail, LocalDate date, LocalTime time);


}
