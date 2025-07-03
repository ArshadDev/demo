package com.sample.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


@GetMapping("/greet")
    public String greetings(){
        return "Hello ! The controller is working fine ... welcome !";
    }
}
