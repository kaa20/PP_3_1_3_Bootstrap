package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserInfo(ModelMap model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String getAllUser(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("listUsers", userService.listUsers());
        model.addAttribute("listRoles", userService.listRoles());
        return "admin";
    }

    @PostMapping("/admin/new")
    public String createUser(@ModelAttribute("newUser") User newUser) {
        List<String> listStr = newUser.getRoles().stream().map(r->r.getRole()).collect(Collectors.toList());
        List<Role> roles = userService.listByRole(listStr);
        newUser.setRoles(roles);
        System.out.println("createUser: " + newUser);
        if (userService.findUserByUsername(newUser.getUsername()) == null) {
            userService.add(newUser);
        }
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

    @PatchMapping("/admin/update/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        List<String> listStr = user.getRoles().stream().map(r->r.getRole()).collect(Collectors.toList());
        List<Role> roles = userService.listByRole(listStr);
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
