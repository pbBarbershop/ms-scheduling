package br.com.pb.barbershop.msscheduling.framework.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@RequiredArgsConstructor
public class SchedulingExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(SchedulingNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
        StandartError error = new StandartError(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<StandartError> idNotFound(IdNotFoundException ex, HttpServletRequest request) {
        StandartError error = new StandartError(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityValidationException.class)
    public ResponseEntity<StandartError> dataIntegrityViolationException(
        DataIntegrityValidationException ex,
        HttpServletRequest request
    ) {
        StandartError error = new StandartError(
            LocalDateTime.now(),
            BAD_REQUEST.value(),
            ex.getMessage(),
            request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<ValidationException> errorList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ValidationException error = new ValidationException(e.getField(), message);
            errorList.add(error);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }
}
