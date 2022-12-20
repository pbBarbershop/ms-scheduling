package br.com.pb.barbershop.msscheduling.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingDTO {
    String clientName;
    String clientContact;
    String clientEmail;
}
