package br.com.pb.barbershop.msscheduling.framework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SchedulingExceptionHandler {
    @ExceptionHandler(value ={SchedulingNotFoundException.class,})
    public ResponseEntity<Object> handlerSchedulingNotFoundException(SchedulingNotFoundException schedulingNotFoundException) {
        SchedulingException schedulingException = new SchedulingException(
                SchedulingNotFoundException.getMessage(),
                SchedulingNotFoundException.getCause(),
                HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(SchedulingException, HttpStatus.NOT_FOUND);
    }
}
