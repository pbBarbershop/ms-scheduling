package br.com.pb.barbershop.msscheduling.aplication.service;


import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;

public interface SchedulingService {

    public SchedulingDTO findById(Long id);
}
