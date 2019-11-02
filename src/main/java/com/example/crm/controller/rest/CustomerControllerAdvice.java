package com.example.crm.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.crm.domain.dto.out.ExceptionResponse;
import com.example.crm.util.exception.CustomerNotFoundException;
import com.example.crm.util.exception.CustomerNoteNotFoundException;
import com.example.crm.util.exception.InvalidCustomerStatusException;
import com.example.crm.util.exception.VersionNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * CustomerControlerlAdvice
 */
@RestController
@ControllerAdvice(basePackages = "com.example.crm.controller.rest.v0.customer")
@Slf4j
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler{


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
    @ExceptionHandler(value  = { IllegalArgumentException.class, IllegalStateException.class
        ,NumberFormatException.class, VersionNotFoundException.class , InvalidCustomerStatusException.class})
    public ResponseEntity<ExceptionResponse> handleConflict(RuntimeException e, WebRequest request) {
      return error(HttpStatus.CONFLICT, e);
    }
    @ExceptionHandler({CustomerNotFoundException.class, CustomerNoteNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundException(Exception e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

    private ResponseEntity<ExceptionResponse> error(HttpStatus status, Exception e) {
        
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().error(status.value())
                                    .message(e.getMessage())
                                    .httpCodeMessage(status.getReasonPhrase())
                                    .exceptionMessage(e.getLocalizedMessage())
                                    .build();
        log.error("Exception : {}  ", e);
        return new ResponseEntity <ExceptionResponse> (exceptionResponse, status);
    }

   
}