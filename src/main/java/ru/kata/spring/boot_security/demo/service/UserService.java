package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean addRole(Role role);
    Role findRoleByName(String name);
    List<Role> listRoles();
    Role findRoleById(Long id);
    List<Role> listByRole(List<String> name);
    boolean add(User user);
    boolean addUserWithRole(User user, String rolename);
    List<User> listUsers();
    void delete(Long id);
    void update(User user);
    User findUserById(Long id);
    User findUserByUsername(String userName);
}
