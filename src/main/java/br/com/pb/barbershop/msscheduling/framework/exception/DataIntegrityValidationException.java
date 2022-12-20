package br.com.pb.barbershop.msscheduling.framework.exception;

public class DataIntegrityValidationException extends RuntimeException {

    public DataIntegrityValidationException(String message) {
        super(message);
    }
}