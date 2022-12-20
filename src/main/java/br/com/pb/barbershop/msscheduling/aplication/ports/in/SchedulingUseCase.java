package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingResponse;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.exception.DataIntegrityValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingUseCase implements SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final ModelMapper mapper;



    @Override
    public SchedulingResponse createScheduling(SchedulingDTO schedulingDTO) {
        var scheduling = mapper.map(schedulingDTO, Scheduling.class);
        var schedulingOp = schedulingRepository.findByDate(schedulingDTO.getDate());

        if(schedulingOp.isPresent()){
            throw new DataIntegrityValidationException("Data para agendamento não disponível");
        }
        schedulingRepository.save(scheduling);;
        return mapper.map(scheduling, SchedulingResponse.class);
    }

}

