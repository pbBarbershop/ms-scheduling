package br.com.pb.barbershop.msscheduling.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingFilter {
    private Long id;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private LocalDate date;
}
