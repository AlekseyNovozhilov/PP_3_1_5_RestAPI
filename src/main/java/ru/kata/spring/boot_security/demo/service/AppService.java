package ru.kata.spring.boot_security.demo.service;




import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface AppService extends UserDetailsService {

    User findBiId(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void removeBiId(Long id);

    List<User> getAllUsers();

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role findRoleByName(String name);
    User findByUserName(String username);


}
