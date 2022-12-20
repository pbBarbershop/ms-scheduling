package br.com.pb.barbershop.msscheduling.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingDTO {
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    private String barberName;
}
