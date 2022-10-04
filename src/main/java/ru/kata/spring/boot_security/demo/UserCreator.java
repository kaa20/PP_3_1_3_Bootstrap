package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserCreator {

    @Autowired
    private UserServiceImpl userService;

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        userService.addRole(role1);
        userService.addRole(role2);

        List<Role> roleAdmin = new ArrayList<>();
        List<Role> roleUser = new ArrayList<>();

        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("Ad", "Min", 100, "admin@mail.ru", "admin", roleAdmin);
        User user2 = new User("U", "Ser", 1,"user@mail.ru", "user", roleUser);

        userService.add(user1);
        userService.add(user2);
    }
}
