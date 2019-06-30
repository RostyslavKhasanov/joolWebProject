package ua.jool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController  {

    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }

}
