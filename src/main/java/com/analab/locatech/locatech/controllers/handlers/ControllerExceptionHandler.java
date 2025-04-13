package com.analab.locatech.locatech.controllers.handlers;

import com.analab.locatech.locatech.dtos.StandardErrorHandlerDTO;
import com.analab.locatech.locatech.dtos.ValidationErrorDTO;
import com.analab.locatech.locatech.services.exceptions.ConstraintException;
import com.analab.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorHandlerDTO> handlerResourceNotFoundException(ResourceNotFoundException error){
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new StandardErrorHandlerDTO(error.getMessage(), status.value()));
    }

    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<StandardErrorHandlerDTO> handlerConstraintException(ConstraintException error){
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StandardErrorHandlerDTO(error.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException error){
        var status = HttpStatus.BAD_REQUEST;
        var errorList = new ArrayList<String>();
        for (var e: error.getBindingResult().getFieldErrors()){
            errorList.add(e.getField() + ": " + e.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(new ValidationErrorDTO(errorList, status.value()));
    }
}
