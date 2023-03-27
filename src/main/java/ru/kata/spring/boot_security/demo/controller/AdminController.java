package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String show(Principal principal, Model model) {
        User currentUser = userService.findByEmail(principal.getName());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", currentUser);
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin";
    }

    @GetMapping("newUser")
    public String newUser(Principal principal, Model model) {
        User currentUser = userService.findByEmail(principal.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("roles", roleService.findAllRoles());
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.findByName(role.getName()))
                .collect(Collectors.toSet()));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}/edit")
    public String editUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.findAllRoles());
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.findByName(role.getName()))
                .collect(Collectors.toSet()));
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeBiId(id);
        return "redirect:/admin";
    }
}
