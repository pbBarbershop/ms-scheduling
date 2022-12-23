package br.com.pb.barbershop.msscheduling.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingDTO {
    private Long id;
    private String clientName;
    private String clientContact;
    private String clientEmail;
    private LocalDateTime dateScheduling;
}
