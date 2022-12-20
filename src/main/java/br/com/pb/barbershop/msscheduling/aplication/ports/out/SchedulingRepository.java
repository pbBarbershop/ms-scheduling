package br.com.pb.barbershop.msscheduling.aplication.ports.out;

import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
