package az.unibank.mscard.exception.handler;

import az.unibank.mscard.exception.CardAlreadyExistException;
import az.unibank.mscard.exception.CardNotFoundException;
import az.unibank.mscard.exception.ServiceProviderException;
import az.unibank.mscard.model.view.ErrorView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CardAlreadyExistException.class)
    public ResponseEntity<ErrorView> handle(CardAlreadyExistException exception) {
        log.error("CardAlreadyExistException: {}", exception);
        return new ResponseEntity<>(new ErrorView(exception.getCode(), exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorView> handle(CardNotFoundException exception) {
        log.error("CardNotFoundException: {}", exception.getMessage());
        return new ResponseEntity<>(new ErrorView(exception.getCode(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceProviderException.class)
    public ResponseEntity<ErrorView> handle(ServiceProviderException exception) {
        log.error("ServiceProviderException: {}", exception.getMessage());
        return new ResponseEntity<>(new ErrorView(exception.getCode(), exception.getMessage()), HttpStatusCode.valueOf(Integer.parseInt(exception.getCode())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorView> handle(Exception exception) {
        log.error("Unknown Exception: {}", exception.getMessage());
        return new ResponseEntity<>(new ErrorView("500", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
