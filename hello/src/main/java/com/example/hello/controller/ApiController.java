package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //해당 Class 는 REST API 를 처리하는 Controller
@RequestMapping("/api") //RequestMapping 은 URI 지정해주는 어노테이션
public class ApiController {

    @GetMapping("/hello") //http://localhost:8080/api/hello
    public String hello(){
        return "hello spring boot!";
    }
}
