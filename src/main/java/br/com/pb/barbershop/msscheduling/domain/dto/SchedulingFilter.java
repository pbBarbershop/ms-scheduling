package br.com.pb.barbershop.msscheduling.domain.dto;

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

    private Long id;
    private String clientName;
    private String clientContact;
    private String clientEmail;
    private LocalDateTime dateScheduling;
}
