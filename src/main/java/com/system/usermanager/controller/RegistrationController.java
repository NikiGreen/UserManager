package com.system.usermanager.controller;


import com.system.usermanager.model.User;
import com.system.usermanager.model.parametr.Role;
import com.system.usermanager.model.parametr.Status;
import com.system.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, String firstName, String lastName, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        /*user.setActive(Collections.singleton(Status.ACTIVE));*/
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setCreatedAt(new SimpleDateFormat("HH:mm:ss_dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        userRepository.save(user);

        return "redirect:/login";
    }

    @PostMapping("/user/new")
    public String peoples(User user, String firstName, String lastName, @RequestParam String status, @RequestParam String role, Map<String, Object> model) {

        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb == null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            /*user.setActive(Collections.singleton(Status.valueOf(status)));*/
            if(status.equals("ACTIVE")){
                user.setActive(true);
            }else
                user.setActive(false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(Role.valueOf(role)));
            user.setCreatedAt(new SimpleDateFormat("HH:mm:ss_dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            userRepository.save(user);
        }

        Iterable<User> users = userRepository.findAll();


        model.put("users", users);

        return "redirect:/user";
    }
}