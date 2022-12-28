package br.com.pb.barbershop.msscheduling.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class SchedulingUseCaseTest {

    @InjectMocks
    private SchedulingUseCase schedulingService;

    @Mock
    private SchedulingRepository repository;

    @Spy
    private ModelMapper mapper;

    private static final Long ID = 1L;

    @Test
    void whenUpdateThenReturnSucess() {
        Scheduling scheduling = new Scheduling();
        SchedulingDTO request = new SchedulingDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(scheduling));
        Mockito.when(repository.save(any())).thenReturn(scheduling);

        Scheduling response = schedulingService.update(ID, request);

        assertEquals(scheduling, response);
        verify(repository).save(any());
    }
}
