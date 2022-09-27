package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    public boolean add(Role user) {
        em.persist(user);
        return true;
    }

    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    public List<Role> listRoles() {
        return em.createQuery("select s from Role s", Role.class).getResultList();
    }

    public Role findByName(String name) {
        return em.createQuery("select u FROM Role u WHERe u.role = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

    public List<Role> listByName(List<String> name) {
        return  em.createQuery("select u FROM Role u WHERe u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList();
    }

}
