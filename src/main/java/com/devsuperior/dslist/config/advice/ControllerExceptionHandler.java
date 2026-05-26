package com.devsuperior.dslist.config.advice;

import com.devsuperior.dslist.exceptions.EntityCreateFailedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntity(DataIntegrityViolationException exception) {

        return ResponseEntity.badRequest()
                .body(ExceptionHandlerDTO.builder()
                        .message(String.format("Erro ao cadastrar usuário ou usuário já cadastrado: %s.", exception.getMessage()))
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFound(EntityNotFoundException exception) {
        final var erroMessage = "%d - %s: %s.".formatted(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionHandlerDTO.builder()
                        .message(erroMessage)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

    @ExceptionHandler(EntityCreateFailedException.class)
    public ResponseEntity threatUnprocessableEntity(EntityCreateFailedException exception) {
        final var erroMessage = "%d - %s: %s.".formatted(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(),
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ExceptionHandlerDTO.builder()
                        .message(erroMessage)
                        .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                        .build()
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity threatGeneralRuntimeException(RuntimeException exception) {

        return ResponseEntity.internalServerError()
                .body(ExceptionHandlerDTO.builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception) {

        return ResponseEntity.internalServerError()
                .body(ExceptionHandlerDTO.builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
                );
    }
}
