package br.com.pb.barbershop.msscheduling.aplication.service;

import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingResponse;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;

import java.util.List;

public interface SchedulingService {
    public SchedulingResponse createScheduling(SchedulingDTO scheduling);


}
