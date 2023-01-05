package br.com.pb.barbershop.msscheduling.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepositoryPortOut;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class SchedulingServiceTest {

    @InjectMocks
    private SchedulingUseCase schedulingService;

    @Mock
    private SchedulingRepositoryPortOut repository;

    @Spy
    private ModelMapper mapper;

    private static final Long ID = 1L;
}
