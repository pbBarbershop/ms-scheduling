package br.com.pb.barbershop.msscheduling.framework.adapter.out;

import br.com.pb.barbershop.msscheduling.aplication.port.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDto;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService  {

    private final ModelMapper modelMapper;
    private final SchedulingRepository schedulingRepository;
    public Scheduling create(SchedulingDto schedulingRequest){
        Scheduling scheduling = modelMapper.map(schedulingRequest, Scheduling.class);
        schedulingRepository.save(scheduling);
        return scheduling;
    }


}
