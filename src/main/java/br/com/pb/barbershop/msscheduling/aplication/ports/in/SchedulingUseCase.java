package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingUseCase implements SchedulingService {

    SchedulingRepository schedulingRepository;

    public SchedulingUseCase(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    @Override
    public Scheduling createScheduling(Scheduling scheduling) {
        //Lógica de Negócios
        return schedulingRepository.save(scheduling);

    }

    @Override
    public String updateScheduling(Scheduling scheduling) {
        //Lógica de Negócios
        schedulingRepository.save(scheduling);
        return "Success";
    }

    @Override
    public String deleteScheduling(String schedulingId) {
        //Lógica de Negócios
        schedulingRepository.deleteById(schedulingId);
        return "Success";
    }

    @Override
    public Scheduling getScheduling(String schedulingId) {
        //Lógica de Negócios
        return schedulingRepository.findById(SchedulingId).get();
    }

    @Override
    public List<Scheduling> getAllSchedulings() {
        //Lógica de Negócios
        return schedulingRepository.findAll();
    }
}

