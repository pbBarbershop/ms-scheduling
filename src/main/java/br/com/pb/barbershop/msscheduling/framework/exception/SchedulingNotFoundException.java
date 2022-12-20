package br.com.pb.barbershop.msscheduling.framework.exception;

import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;

public class SchedulingNotFoundException extends RuntimeException {

    public SchedulingNotFoundException(String message) {
        super(message);
    }

    public SchedulingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
