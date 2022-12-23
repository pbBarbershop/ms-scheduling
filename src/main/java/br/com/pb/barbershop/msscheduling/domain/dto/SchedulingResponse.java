package br.com.pb.barbershop.msscheduling.domain.dto;

import br.com.pb.barbershop.msscheduling.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingResponse {
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private Status status;
    private LocalDate date;
    private LocalTime time;
    private String barberName;
}
