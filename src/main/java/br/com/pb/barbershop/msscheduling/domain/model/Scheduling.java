package br.com.pb.barbershop.msscheduling.domain.model;

import br.com.pb.barbershop.msscheduling.domain.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private Status status = Status.AGUARDANDO_PAGAMENTO;
    private LocalDateTime date;
    private String barber;


}