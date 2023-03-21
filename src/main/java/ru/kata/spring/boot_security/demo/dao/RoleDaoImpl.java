package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

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
    public Role findRoleByName(String name) {
        return getAllRoles()
                .stream()
                .filter(r -> Objects.equals(r.getName(), name))
                .findFirst()
                .orElse(new Role());
    }
}
