package com.app.todo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(origins =  {"${app.dev.frontend.local}"})
public class HelloController {

    @GetMapping(value = "/api/message")
    public Map<String, String> index() {
       // return Collections.singletonMap("message", "Greetings from Spring Boot and Angular!");
         return Collections.singletonMap("message", "Welcome to Deployment");
    }

}
