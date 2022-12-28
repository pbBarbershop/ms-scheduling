package br.com.pb.barbershop.msscheduling.domain.dto;

import br.com.pb.barbershop.msscheduling.domain.enums.Service;
import br.com.pb.barbershop.msscheduling.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingFilter {
    @NotBlank
    @Pattern(regexp = "^([a-zA-ZãÃéÉíÍóÓêÊôÔáÁ\s])+$", message =
            "Nome deve conter apenas letras e não deve ser em branco.")
    private String clientName;
    @Length(min = 11, max = 11, message =
            "Deve conter ddd + número (11 caracteres ex. 11111111111).")
    @Pattern(regexp = "^([0-9])+$", message =
            "Deve conter apenas números, ddd + número (11 caracteres ex. 11111111111)")
    private String clientPhone;
    @Email
    private String clientEmail;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    @NotBlank
    private String barberName;
    private Service service;
}