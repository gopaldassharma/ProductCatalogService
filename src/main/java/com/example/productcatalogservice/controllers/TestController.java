package com.example.productcatalogservice.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String getMessage(){
        return "welcome to Project Module";
    }
}
