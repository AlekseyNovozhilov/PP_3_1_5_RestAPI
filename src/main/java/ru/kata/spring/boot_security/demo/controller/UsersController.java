package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UsersService;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService us;

    @Autowired
    public UsersController(UsersService us) {
        this.us = us;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("allUsers", us.getAllUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String showUserBiId(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", us.getUserBiId(id));
        return "users/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        us.saveUser(user);
        return "redirect:/users/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", us.getUserBiId(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        us.updateUser(user);
        return "redirect:/users/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        us.removeUserBiId(id);
        return "redirect:/users/users";
    }

}
