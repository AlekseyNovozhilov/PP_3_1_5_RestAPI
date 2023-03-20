package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AppServiceImpl;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppServiceImpl appServiceImpl;

    @Autowired
    public AdminController(AppServiceImpl appServiceImpl) {
        this.appServiceImpl = appServiceImpl;
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("allUsers", appServiceImpl.getAllUsers());
        return "html/users";
    }

    @GetMapping("/{id}")
    public String showBiId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", appServiceImpl.findBiId(id));
        model.addAttribute("role", appServiceImpl.getAllRoles());
        return "html/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", appServiceImpl.getAllRoles());
        return "html/new";
    }

    @PostMapping("/{id}")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "name", required = false) Set<Role> roles) {
        appServiceImpl.saveUser(user);
        user.setRoles(roles);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        //System.out.println(appServiceImpl.getAllRoles());
        model.addAttribute("user", appServiceImpl.findBiId(id));
        model.addAttribute("allRoles", appServiceImpl.getAllRoles());
        return "html/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {

        appServiceImpl.updateUser(user);
        //System.out.println(user.getRoles());
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        appServiceImpl.removeBiId(id);
        return "redirect:/admin/users";
    }

}
