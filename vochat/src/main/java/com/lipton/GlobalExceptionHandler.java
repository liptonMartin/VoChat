package com.lipton;

import com.lipton.common.models.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> handleException(ResponseStatusException exception) {
        var error = new ErrorDto(false, exception.getReason(), exception.getStatusCode());

        return ResponseEntity.status(error.status()).body(error);
    }
}
