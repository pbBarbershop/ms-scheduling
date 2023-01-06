package br.com.pb.barbershop.msscheduling.domain.dto;

import br.com.pb.barbershop.msscheduling.domain.enums.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
