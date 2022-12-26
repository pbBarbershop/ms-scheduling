package br.com.pb.barbershop.msscheduling.domain.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    private Long id;
    private String clientName;
    private String clientContact;
    private String clientEmail;
    private LocalDateTime dateScheduling;


}