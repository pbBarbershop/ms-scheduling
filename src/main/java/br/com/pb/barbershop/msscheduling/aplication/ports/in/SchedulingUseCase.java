package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulingUseCase implements SchedulingService {

    private final SchedulingRepository repository;

    private final ModelMapper mapper;

    @Override
    public SchedulingDTO findById(Long id){
        return mapper.map(getScheduling(id), SchedulingDTO.class);
    }

    public Scheduling getScheduling(Long id){
        Optional<Scheduling> scheduling = repository.findById(id);
        return scheduling.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado!"));
    }

}


