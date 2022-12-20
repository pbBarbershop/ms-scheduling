package br.com.pb.barbershop.msscheduling.aplication.service;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;

import java.util.List;

public interface SchedulingService {
    //private final SchedulingRepository schedulingRepository;
    /*public Scheduling createScheduling(Scheduling scheduling);
    public String updateScheduling(Scheduling scheduling);
    public String deleteScheduling(String schedulingId);
    public Scheduling getScheduling(String schedulingId);*/
    public List<Scheduling> getAllSchedulings();

}
