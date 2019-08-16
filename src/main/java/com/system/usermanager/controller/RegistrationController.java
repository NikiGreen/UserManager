package com.system.usermanager.controller;


import com.system.usermanager.model.User;
import com.system.usermanager.model.parametr.Role;
import com.system.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }

    @PostMapping("/user/new")
    public String peoples(User user, Boolean status, @RequestParam String role, Map<String, Object> model) {

        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (status == null)
            status = false;
        if (userFromDb == null) {
            user.setActive(status);
            user.setRoles(Collections.singleton(Role.valueOf(role)));
            userRepository.save(user);
        }

        Iterable<User> users = userRepository.findAll();


        model.put("users", users);

        return "redirect:/user";
    }
}