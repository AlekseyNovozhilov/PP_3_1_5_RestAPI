package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;


import java.util.List;

public interface RoleDao {
    Role findById(Long id);

    Role findByName(String name);

    List<Role> getAllRoles();
}
