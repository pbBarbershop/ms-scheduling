package br.com.pb.barbershop.msscheduling.mocks;

import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.enums.Service;
import br.com.pb.barbershop.msscheduling.domain.enums.Status;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import br.com.pb.barbershop.msscheduling.domain.model.user.User;

import java.time.LocalDateTime;

public class SchedulingMock {
    public static SchedulingDTO getSchedulingDTOMock() {
        return SchedulingDTO.builder()
                .barberId(1L)
                .customerId(2L)
                .status(Status.AGUARDANDO_PAGAMENTO)
                .dateTime(LocalDateTime.of(2023, 01, 21, 17, 00))
                .service(Service.CABELO)
                .build();
    }

    public static Scheduling getSchedulingMock() {
        return Scheduling.builder()
                .id(1L)
                .barberId(1L)
                .customerId(2L)
                .status(Status.AGUARDANDO_PAGAMENTO)
                .dateTime(LocalDateTime.of(2023, 01, 21, 17, 00))
                .service(Service.CABELO).build();
    }

    public static User getUserMock() {
        return User.builder()
                .id(1L)
                .name("João")
                .email("joao@mail.com")
                .phone("55985082020")
                .build();
    }
}
