package com.example.proyectointegradororm.exceptions;
import com.example.proyectointegradororm.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcepetion extends Exception{
    //Incluir el Logger
    private final static org.apache.log4j.Logger LOGGER = Logger.getLogger(GlobalExcepetion.class.getName());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> procesarErrorRNF(ResourceNotFoundException e){
        LOGGER.error("Error. No se ha encontrado el recurso solicitado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<String> procesarErrorRBR(ResourceBadRequestException e){
        LOGGER.error("Error. No se ha podido procesar la solicitud.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
