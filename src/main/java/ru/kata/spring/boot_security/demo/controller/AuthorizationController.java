package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }
/*
    @GetMapping("/")
    public String showPageAuthorization() {
        return "/html/login";
    }*/
}
