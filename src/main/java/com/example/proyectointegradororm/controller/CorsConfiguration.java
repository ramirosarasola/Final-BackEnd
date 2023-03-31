package com.example.proyectointegradororm.controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/**")
public class CorsController {

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        headers.add("Access-Control-Max-Age", "86400");
        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }
}
