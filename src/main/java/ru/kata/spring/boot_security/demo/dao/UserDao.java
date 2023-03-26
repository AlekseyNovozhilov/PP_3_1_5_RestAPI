package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    User findByName(String name);

    User findByEmail(String email);

    User findBiId(Long id);

    void saveUser(User uer);

    void updateUser(User user);

    void removeBiId(Long id);

    List<User> getAllUsers();

}
