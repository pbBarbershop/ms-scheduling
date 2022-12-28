package br.com.pb.barbershop.msscheduling.domain.dto;
import br.com.pb.barbershop.msscheduling.domain.enums.Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulingDTO {
    private Long id;
    @NotBlank(message = "invalid field")
    @Pattern(regexp = "^([a-zA-ZãÃéÉíÍóÓêÊôÔáÁ\s])+$", message = "field must contain letters only")
    private String clientName;
    @NotBlank(message = "invalid field")
    @Pattern(regexp = "^[0-9]{11}+$", message = "invalid phone number")
    private String clientPhone;
    @Email(message = "email address must be valid")
    private String clientEmail;
    @NotNull(message = "invalid field")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotNull(message = "invalid field")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;
    @NotBlank
    private String barberName;
    private Service service;
}

