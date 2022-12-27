package br.com.pb.barbershop.msscheduling.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class SchedulingServiceTest {

    @InjectMocks
    private SchedulingService schedulingService;

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
