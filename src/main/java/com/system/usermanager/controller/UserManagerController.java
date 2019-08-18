package com.system.usermanager.controller;

import com.system.usermanager.model.User;
import com.system.usermanager.model.parametr.Role;
import com.system.usermanager.model.parametr.Status;
import com.system.usermanager.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserManagerController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String greeting(Map<String, Object> model
    ) {
        return "/greeting";
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "/login";
    }

    @GetMapping("/user")
    public String users(Model model, Authentication authentication) {
        Iterable<User> users = userRepository.findAll();
        String sessionRole= String.valueOf(authentication.getAuthorities());

        model.addAttribute("users", users);
        model.addAttribute("sessionRole",sessionRole);
        return "/users";
    }

    @PostMapping("/user/{id}")
    public String info(@PathVariable(value = "id") String id, Map<String, Object> model) {
        User user = userRepository.findById(Long.valueOf(id)).orElse(new User());
        model.put("username", user.getUsername());
        model.put("firstName", user.getFirstName());
        model.put("lastName", user.getLastName());
        model.put("createdAt", user.getCreatedAt());

        return "/profileInfo";
    }
    @GetMapping("/user/{id}/edit")
    public String getUserInfo(@PathVariable(value = "id") String id, Map<String, Object> model) {
        User user = userRepository.findById(Long.valueOf(id)).orElse(new User());
        model.put("username", user.getUsername());
        model.put("password", user.getPassword());
        model.put("firstName", user.getFirstName());
        model.put("lastName", user.getLastName());
        model.put("createdAt", user.getCreatedAt());

        return "/profile";
    }

    @PostMapping("/user/{id}/edit")
    public String edit(User user,@RequestParam String createdAt, @RequestParam String status, @RequestParam String role, @PathVariable("id") String id, Map<String, Object> model) {
        /*userRepository.deleteById(user.getId());*/
            /*user.setActive(Collections.singleton(Status.valueOf(status)));*/
            if(status.equals("ACTIVE")){
                user.setActive(true);
            }else
                user.setActive(false);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        user.setCreatedAt(createdAt);
            userRepository.save(user);


        Iterable<User> users = userRepository.findAll();


        model.put("users", users);

        return "redirect:/user";
    }

    @PostMapping("byname")
    public String byname(@RequestParam String name, Map<String, Object> model) {
        Iterable<User> users;

        if (name != null && !name.isEmpty()) {
            users = userRepository.findAllByUsername(name);
        } else {
            users = userRepository.findAll();
            model.put("users", users);
            return "/users";
        }

        model.put("users", users);

        return "/users";
    }

    @PostMapping("byrole")
    public String byrole(@RequestParam String role, Map<String, Object> model) {
        Iterable<User> users;

        if (role != null && !role.isEmpty()) {
            users = userRepository.findAllByRoles(Collections.singleton(Role.valueOf(role)));
        } else {
            users = userRepository.findAll();
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
