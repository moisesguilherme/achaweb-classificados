package br.com.achaweb.controllers.exceptions;

import br.com.achaweb.services.exceptions.EntityCategoryNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityCategoryNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityCategoryNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(String.valueOf(Instant.now()));
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
