package br.com.pb.barbershop.msscheduling.framework.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(Long id) {
        super(String.format("O usuario de id %s nao existe!", id));
    }
}
