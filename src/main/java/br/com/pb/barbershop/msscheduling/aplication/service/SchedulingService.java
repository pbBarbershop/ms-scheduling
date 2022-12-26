package br.com.pb.barbershop.msscheduling.aplication.service;



import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface SchedulingService {
    Page<Scheduling> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable);

    void delete(Long id);

    public SchedulingDTO findById(Long id);

}
