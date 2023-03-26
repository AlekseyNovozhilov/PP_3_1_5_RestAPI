package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;



@Repository
public class UserDAOImpl implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByName(String name) {
        return getAllUsers()
                .stream()
                .filter(user -> Objects.equals(user.getName(), name))
                .findFirst()
                .orElse(new User());
    }

    @Override
    public User findByEmail(String email) {
        return getAllUsers()
                .stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst()
                .orElse(new User());
    }

    @Override
    public User findBiId(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeBiId(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }
}
