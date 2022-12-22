package br.com.pb.barbershop.msscheduling.aplication.ports.out;

import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;

import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    Page<Scheduling> findAll(Example example, Pageable pageable);
}
