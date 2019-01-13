package com.n26.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public final class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NumberFormatException.class})
    public final ResponseEntity<Object> handleBigDecimalParsingException() {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        return buildResponseEntity(httpStatus);
    }

    @ExceptionHandler({DateTimeParseException.class})
    public final ResponseEntity<Object> handleDateTimeParseException() {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        return buildResponseEntity(httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        super.handleMethodArgumentNotValid(ex, headers, status, request);
        return buildResponseEntity(getHttpStatus(ex));
    }

    private HttpStatus getHttpStatus(MethodArgumentNotValidException ex) {
        String exceptionCause = ex.getMessage();
        if(exceptionCause.contains("null") || exceptionCause.contains("blank")) {
            return HttpStatus.BAD_REQUEST;
        }
        if(exceptionCause.contains("future")) {
            return HttpStatus.UNPROCESSABLE_ENTITY;
        }
        if(exceptionCause.contains("old")) {
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.BAD_REQUEST;
    }

    private final ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).build();
    }
}
