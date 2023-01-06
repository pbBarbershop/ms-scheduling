package br.com.pb.barbershop.msscheduling.domain.dto;

import br.com.pb.barbershop.msscheduling.domain.enums.Service;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingFilter {

    private Long customerId;
    private Long barberId;
    private LocalDateTime dateTime;
    private Service service;
}
