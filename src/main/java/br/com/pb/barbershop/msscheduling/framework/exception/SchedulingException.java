package br.com.pb.barbershop.msscheduling.framework.exception;

import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SchedulingException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public SchedulingException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }
}

