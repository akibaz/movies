package dev.akibaz.movies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiException apiException = new ApiException(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(
                apiException,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiException> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApiException apiException = new ApiException(
                HttpStatus.BAD_REQUEST,
                "Provided parameter type mismatch. Provide ObjectId (24 character hexadecimal string).",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }
}
