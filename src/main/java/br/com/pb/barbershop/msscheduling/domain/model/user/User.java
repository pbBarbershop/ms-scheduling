package br.com.pb.barbershop.msscheduling.domain.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@NonNull
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    @Email
    private String email;

    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profile;
}
