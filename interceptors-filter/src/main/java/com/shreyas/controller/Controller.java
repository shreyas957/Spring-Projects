package com.shreyas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/api/home")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/home2")
    public String home2() {
        return "Hello World!";
    }
}
