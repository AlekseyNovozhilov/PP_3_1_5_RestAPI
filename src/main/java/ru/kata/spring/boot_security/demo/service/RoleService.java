package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
@Component
public interface RoleService {

    Role findById(Long id);

    Role findByName(String name);

    List<Role> findAllRoles();

    List<String> findNameAllRoles();
}
