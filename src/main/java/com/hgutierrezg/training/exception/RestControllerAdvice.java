package com.hgutierrezg.training.exception;

import com.hgutierrezg.training.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<?> handleRuntimeException(Throwable ex, WebRequest request) {

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .details(ex.getMessage())
                .build();
        log.error("Error - Handling rest request: {}", request, ex);

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<?> handleClientErrorException(Throwable ex, WebRequest request) {

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .details(ex.getMessage())
                .build();
        log.error("Error - Handling rest request: {}", request, ex);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
