package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SchedulingUseCase implements SchedulingService {

    SchedulingRepository schedulingRepository;
    @Override
    public List<Scheduling> getAllSchedulings() {
        //Lógica de Negócios
        return schedulingRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (schedulingRepository.existsById(id)) {
            throw new ObjectNotFoundException("Agendamento não encontrado", id);
        }
        schedulingRepository.deleteById(id);
    }
}

