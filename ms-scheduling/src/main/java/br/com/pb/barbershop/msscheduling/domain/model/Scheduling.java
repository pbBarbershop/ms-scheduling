package br.com.pb.barbershop.msscheduling.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Scheduling {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String clientContact;
    private String clientEmail;
    //private LocalDate date;

    public Scheduling(String clientName, String clientContact, String clientEmail) {
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.clientEmail = clientEmail;
    }
}
