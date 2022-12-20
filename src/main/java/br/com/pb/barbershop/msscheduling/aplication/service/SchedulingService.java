package br.com.pb.barbershop.msscheduling.aplication.service;

import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;

import java.util.List;

public class SchedulingService {
    public Scheduling createScheduling(Scheduling scheduling);
    public String updateScheduling(Scheduling scheduling);
    public String deleteScheduling(String schedulingId);
    public Scheduling getScheduling(String schedulingId);
    public List<Scheduling> getAllSchedulings();

}
