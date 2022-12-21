package br.com.pb.barbershop.msscheduling.aplication.service;


import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface SchedulingService {
    Page<SchedulingDTO> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable);

    void delete(Long id);
}
