package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface Dao {
    User getUserBiId(int id);

    void saveUser(User uer);

    void updateUser(User user);

    void removeUserBiId(int id);

    List<User> getAllUsers();

}
