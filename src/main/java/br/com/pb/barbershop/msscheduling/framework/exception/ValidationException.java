package br.com.pb.barbershop.msscheduling.framework.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationException {
    private String field;
    private String errorMessage;
}

