package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;

public interface SchedulingUseCase {
    public Scheduling update(Long id, SchedulingDTO request);
}
