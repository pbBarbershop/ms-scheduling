package br.com.pb.barbershop.msscheduling.reponse;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SchedulingResponseDTO {
    String clientName;
    String clientContact;
    String clientEmail;
}