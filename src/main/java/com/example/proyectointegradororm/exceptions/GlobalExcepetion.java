package com.example.proyectointegradororm.exceptions;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcepetion extends Exception{
    //Incluir el Logger
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> procesarErrorRNF(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<String> procesarErrorRBR(ResourceBadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
