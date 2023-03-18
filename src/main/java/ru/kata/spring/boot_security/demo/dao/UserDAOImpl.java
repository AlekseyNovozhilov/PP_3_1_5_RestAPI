package ru.kata.spring.boot_security.demo.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public class UserDAOImpl implements Dao {
    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public User getUserBiId(int id) {
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
    public void removeUserBiId(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
