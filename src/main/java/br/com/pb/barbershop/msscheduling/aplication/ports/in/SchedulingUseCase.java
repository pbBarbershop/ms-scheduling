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

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SchedulingUseCase implements SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final ModelMapper mapper;



    @Override
    public SchedulingResponse createScheduling(SchedulingDTO schedulingDTO) {
        schedulingValidation(schedulingDTO);
        var scheduling = mapper.map(schedulingDTO, Scheduling.class);
        schedulingRepository.save(scheduling);;
        return mapper.map(scheduling, SchedulingResponse.class);
    }

    private void schedulingValidation(SchedulingDTO schedulingDTO) {
        if (LocalDate.now().isAfter(schedulingDTO.getDate())){
            throw new DataIntegrityValidationException("Não é permitido data retroativa, digite uma data correta!");
        }
        var schedulingCheckOne = schedulingRepository.findByClientEmailAndDateAndTime(schedulingDTO.getClientEmail(),
                schedulingDTO.getDate(), schedulingDTO.getTime());

        if(schedulingCheckOne.isPresent()){
            if(!schedulingCheckOne.get().getBarberName().equals(schedulingDTO.getBarberName())){
                throw new DataIntegrityValidationException("Este cliente possui horário marcado com o barbeiro "+schedulingCheckOne.get().getBarberName());
            }else if(schedulingCheckOne.get().getBarberName().equals(schedulingDTO.getBarberName())){
                throw new DataIntegrityValidationException("Este cliente já possui agendamento para este barbeiro no mesmo horário!");
            }
        }
        var schedulingCheckTwo = schedulingRepository.findByDateAndTimeAndBarberName(schedulingDTO.getDate()
                , schedulingDTO.getTime(), schedulingDTO.getBarberName());

        if(schedulingCheckTwo.isPresent()){
            throw new DataIntegrityValidationException("horário de agendamento não disponivel para este barbeiro.");
        }

    }

}

