package br.com.pb.barbershop.msscheduling.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.aplication.ports.out.UserRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.PageableDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import br.com.pb.barbershop.msscheduling.domain.model.user.User;
import br.com.pb.barbershop.msscheduling.framework.exception.GenericException;
import br.com.pb.barbershop.msscheduling.mocks.SchedulingMock;
import br.com.pb.barbershop.msscheduling.mocks.UserMock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class SchedulingServiceTest {

    @InjectMocks
    private SchedulingService service;

    @Mock
    private SchedulingRepositoryPortOut schedulingRepository;

    @Mock
    private UserRepositoryPortOut userRepository;

    @Mock
    private ModelMapper mapper;

    private static final Long ID = 1L;

    private LocalDateTime now = LocalDateTime.of(2023, 01, 24, 17, 00);

    @Test
    void createScheduling() {
        SchedulingDTO schedulingDTO = SchedulingMock.getSchedulingDTOMock();

        when(userRepository.findById(eq(2L))).thenReturn(Optional.of(User.builder().build()));
        when(userRepository.findById(eq(1L))).thenReturn(Optional.of(User.builder().build()));

        when(schedulingRepository.save(any(Scheduling.class))).thenReturn(SchedulingMock.getSchedulingMock());
        when(mapper.map(any(SchedulingDTO.class), eq(Scheduling.class))).thenReturn(SchedulingMock.getSchedulingMock());
        when(mapper.map(any(Scheduling.class), eq(SchedulingDTO.class)))
            .thenReturn(SchedulingMock.getSchedulingDTOMock());

        SchedulingDTO result = service.create(schedulingDTO);

        assertEquals(schedulingDTO, result);
        verify(schedulingRepository).save(any(Scheduling.class));
    }

    @Test
    void shouldCreateScheduling_customerOrBarberDoesNotExist() {
        SchedulingDTO schedulingDTO = SchedulingMock.getSchedulingDTOMock();

        when(userRepository.findByIdAndProfile(eq(1L), eq("ROLE_CUSTOMER"))).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.create(schedulingDTO));
        when(userRepository.findByIdAndProfile(eq(1L), eq("ROLE_CUSTOMER"))).thenReturn(Optional.of(UserMock.getUserMock()));

        when(userRepository.findByIdAndProfile(eq(2L), eq("ROLE_EMPLOYEE"))).thenReturn(Optional.empty());
        assertThrows(GenericException.class, () -> service.create(schedulingDTO));

        schedulingDTO.setCustomerId(null);
        assertThrows(GenericException.class, () -> service.create(schedulingDTO));

        schedulingDTO.setCustomerId(1L);
        schedulingDTO.setBarberId(null);
        assertThrows(GenericException.class, () -> service.create(schedulingDTO));
    }

    @Test
    void shouldThrowException_WhenSchedulingDTO_IsAfterNow() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserMock.getUserMock()));
        var schedulingDTOMock = SchedulingMock.getSchedulingDTOMock();
        try (var localDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTime.when(() -> LocalDateTime.now()).thenReturn(now);
            assertThrows(GenericException.class, () -> service.create(schedulingDTOMock));
        }
    }

    @Test
    void findById_invalidId() {
        when(schedulingRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.findById(ID));
        verify(schedulingRepository, times(1)).findById(ID);
    }

    @Test
    void shouldTryUpdateScheduling_customerOrBarberDoesNotExist() {
        SchedulingDTO schedulingDTO = SchedulingMock.getSchedulingDTOMock();

        when(userRepository.findById(eq(1L))).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.update(ID, schedulingDTO));
        when(userRepository.findById(eq(1L))).thenReturn(Optional.of(UserMock.getUserMock()));

        when(userRepository.findById(eq(2L))).thenReturn(Optional.empty());
        assertThrows(GenericException.class, () -> service.update(ID, schedulingDTO));

        schedulingDTO.setCustomerId(null);
        assertThrows(GenericException.class, () -> service.update(ID, schedulingDTO));

        schedulingDTO.setCustomerId(1L);
        schedulingDTO.setBarberId(null);
        assertThrows(GenericException.class, () -> service.update(ID, schedulingDTO));
    }

    @Test
    void update_invalidId() {
        SchedulingDTO request = new SchedulingDTO();
        request.setDateTime(LocalDateTime.now().plusHours(1));

        when(schedulingRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(GenericException.class, () -> service.update(ID, request));
        verify(schedulingRepository, times(1)).findById(ID);
    }
}
