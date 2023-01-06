package br.com.pb.barbershop.msscheduling.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.aplication.ports.out.UserRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import br.com.pb.barbershop.msscheduling.domain.model.user.User;
import br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.scheduling.SchedulingJpaRepository;
import br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.user.UserJpaRepository;
import br.com.pb.barbershop.msscheduling.framework.exception.GenericException;
import br.com.pb.barbershop.msscheduling.mocks.SchedulingMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SchedulingServiceTest {

    @InjectMocks
    private SchedulingService service;

    @Mock
    private SchedulingRepositoryPortOut schedulingRepository;

    @Mock
    private UserRepositoryPortOut userRepository;

    @MockBean
    private ModelMapper mapper;

    private static final Long ID = 1L;

    private LocalDateTime now= LocalDateTime.of(2023, 01, 24, 17, 00);

//    @Test
//    void shouldCreate_whenValidInput_thenReturnSchedulingDTO() {
//        SchedulingDTO schedulingDTO = SchedulingMock.getSchedulingDTOMock();
//
//        Scheduling expectedScheduling = SchedulingMock.getSchedulingMock();
//
//        when(service.checkIfUserIdExists(1L)).thenReturn(true);
//        when(service.checkIfUserIdExists(2L)).thenReturn(true);
//
//        doNothing().when(service).schedulingValidation(schedulingDTO);
//
//        when(schedulingRepository.save(expectedScheduling)).thenReturn(expectedScheduling);
//
//        SchedulingDTO result = service.create(schedulingDTO);
//
//        assertEquals(expectedSchedulingDTO, result);
//    }

    @Test
    void shouldThrowException_WhenSchedulingDTO_IsAfterNow() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(SchedulingMock.getUserMock()));
        try(var localDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTime.when(() -> LocalDateTime.now()).thenReturn(now);
            localDateTime.when(() -> LocalDateTime.now().isAfter()).thenReturn(now);
            assertThrows(GenericException.class,
                    () -> service.create(SchedulingMock.getSchedulingDTOMock())
                    );
        }
    }

}
