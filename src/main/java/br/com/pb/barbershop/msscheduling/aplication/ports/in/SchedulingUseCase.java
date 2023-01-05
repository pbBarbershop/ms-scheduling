package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.domain.dto.PageableDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import org.springframework.data.domain.Pageable;

public interface SchedulingUseCase {
    public SchedulingDTO create(SchedulingDTO scheduling);

    public PageableDTO findAll(SchedulingFilter schedulingFilter, Pageable pageable);

    public SchedulingDTO findById(Long id);

    public Scheduling update(Long id, SchedulingDTO request);

    public void delete(Long id);
}
