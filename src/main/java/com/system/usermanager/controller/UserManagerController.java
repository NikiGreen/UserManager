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
public class UserManagerController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model
    ) {
        return "/greeting";
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "/login";
    }

    @GetMapping("/user")
    public String users(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();

        model.put("users", users);

        return "/users";
    }

    @GetMapping("/user/{id}")
    public String userId(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();

        model.put("users", users);

        return "/users";
    }

    @GetMapping("/user/{id}/edit")
    public String edit(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();

        model.put("users", users);

        return "/users";
    }

    @PostMapping("byname")
    public String byname(@RequestParam String name, Map<String, Object> model) {
        Iterable<User> users;

        if (name != null && !name.isEmpty()) {
            users = userRepository.findAllByUsername(name);
        } else {
            users = userRepository.findAll();
            model.put("message", "User exists!");
            model.put("users", users);
            return "/users";
        }

        model.put("users", users);

        return "/users";
    }

    @PostMapping("delete")
    public String delete(@RequestParam String name, Map<String, Object> model) {
        Iterable<User> users;

        if (name != null && !name.isEmpty())
            userRepository.removeAllByUsername(name);

        users = userRepository.findAll();


        model.put("users", users);

        return "redirect:/user";
    }
}
