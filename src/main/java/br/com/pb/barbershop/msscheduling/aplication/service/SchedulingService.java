package br.com.pb.barbershop.msscheduling.aplication.service;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;

import java.util.List;

public interface SchedulingService {
    public List<Scheduling> getAllSchedulings();
    void delete(Long id);
}
