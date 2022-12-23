package br.com.pb.barbershop.msscheduling.domain.model;

import br.com.pb.barbershop.msscheduling.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String clientName;
    private String clientPhone;
    @Email
    private String clientEmail;
    @Enumerated(EnumType.STRING)
    private Status status = Status.AGUARDANDO_PAGAMENTO;
    private LocalDate date;
    private LocalTime time;
    private String barberName;



}