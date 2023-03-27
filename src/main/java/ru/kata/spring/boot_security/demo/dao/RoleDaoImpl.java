package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("From Role", Role.class)
                .getResultList();
    }

    @Override
    public Role findByName(String name) {
        return entityManager.createQuery("select role from Role role where role.name =: name", Role.class)
                .setParameter("name", name).getSingleResult();
    }

}
