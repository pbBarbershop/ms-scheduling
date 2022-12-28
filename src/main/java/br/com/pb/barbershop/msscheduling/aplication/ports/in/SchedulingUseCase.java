package br.com.pb.barbershop.msscheduling.aplication.ports.in;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingResponse;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface SchedulingUseCase {
    Page<Scheduling> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable);

    void delete(Long id);

    public SchedulingResponse createScheduling(SchedulingDTO scheduling);


    public SchedulingDTO findById(Long id);

    public Scheduling update(Long id, SchedulingDTO request);
}


