package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AppServiceImpl;

import java.util.HashSet;
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
        //model.addAttribute("role", appServiceImpl.getAllRoles());
        return "html/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", appServiceImpl.getAllRoles());
        return "html/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user, @RequestParam(name = "ROLE", required = false) String[] role) {
        Role newUserRole = appServiceImpl.findRoleByName(role[0]);
        Set<Role> newRole = new HashSet<>();
        newRole.add(newUserRole);
        user.setRoles(newRole);
        appServiceImpl.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", appServiceImpl.findBiId(id));
        model.addAttribute("allRoles", appServiceImpl.getAllRoles());
        return "html/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam(name = "ROLE", required = false) String[] role) {
        Role newUserRole = appServiceImpl.findRoleByName(role[0]);
        Set<Role> newRole = new HashSet<>();
        newRole.add(newUserRole);
        user.setRoles(newRole);
        appServiceImpl.updateUser(user);
        return "redirect:/admin/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        appServiceImpl.removeBiId(id);
        return "redirect:/admin/users";
    }

}
