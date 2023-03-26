package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Component
public interface UserService extends UserDetailsService {

    User findByName(String name);

    User findByEmail(String email);

    User findBiId(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void removeBiId(Long id);

    List<User> getAllUsers();

}
