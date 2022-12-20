package br.com.pb.barbershop.msscheduling.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String clientContact;
    private String clientEmail;

    //private String ;
    //private LocalDate date;

}