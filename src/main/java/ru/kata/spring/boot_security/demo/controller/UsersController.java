package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AppService;

import java.security.Principal;


@Controller
public class UsersController {

    private final AppService appService;

@Autowired
    public UsersController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/user")
    public String showBiId(Principal principal, Model model) {
    User user = appService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "html/user";
    }


}
