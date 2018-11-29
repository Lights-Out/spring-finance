package net.eleven.finance.web.rest;

import lombok.extern.log4j.Log4j2;
import net.eleven.finance.web.ProductNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Created by eleven on 24.11.2018.
 */
@ControllerAdvice
@Profile("Development")
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleProductNotFoundException(ProductNotFoundException e) {
        log.debug("handling 404 error on a product entry");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleThrowable(Throwable e) {
        log.debug("handling unknown error \n{}", e.getMessage());
        return new ResponseEntity<>("handling unknown error",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationError(
            MethodArgumentNotValidException e) {
        String bodyOfResponse = "Entity is not valid";
        return new ResponseEntity<Object>(bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(), "Validation Failed", ex.getBindingResult().toString());
        return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
    }
}
