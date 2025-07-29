package com.example.corporatesolution.controller;

import com.example.corporatesolution.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    public HelloService helloService;

    @GetMapping("/")
    public String hello() {
        return helloService.sayHello();
    }
}
