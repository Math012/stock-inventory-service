package com.math012.stock_inventory_service.infra.exception.config;

import com.math012.stock_inventory_service.infra.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StructException> handlerResourceNotFoundException(Exception e, WebRequest request){
        var response = new StructException(e.getMessage(),new Date(),request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}