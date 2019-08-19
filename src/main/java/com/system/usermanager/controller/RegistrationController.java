package com.system.usermanager.controller;


import com.system.usermanager.model.UserAccount;
import com.system.usermanager.model.param.Role;
import com.system.usermanager.service.UserMangerService;
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

    public static final String USER_STATUS_ACTIVE = "ACTIVE";

    @Autowired
    private UserMangerService userManagerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserAccount userAccount, String firstName, String lastName, Map<String, Object> model) {
        UserAccount userAccountFromDb = userManagerService.findByUsername(userAccount.getUsername());
        if (userAccountFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        /*user.setActive(Collections.singleton(Status.ACTIVE));*/
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setActive(true);
        userAccount.setRoles(Collections.singleton(Role.USER));
        userAccount.setCreatedAt(new SimpleDateFormat("HH:mm:ss_dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        userManagerService.save(userAccount);
        return "redirect:/login";
    }

    @PostMapping("/user/new")
    public String peoples(UserAccount userAccount, String firstName, String lastName, @RequestParam String status, @RequestParam String role, Map<String, Object> model) {
        UserAccount userAccountFromDb = userManagerService.findByUsername(userAccount.getUsername());
        if (userAccountFromDb == null) {
            userAccount.setFirstName(firstName);
            userAccount.setLastName(lastName);
            /*user.setActive(Collections.singleton(Status.valueOf(status)));*/
            if (USER_STATUS_ACTIVE.equals(status)) {
                userAccount.setActive(true);
            } else
                userAccount.setActive(false);
            userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
            userAccount.setRoles(Collections.singleton(Role.valueOf(role)));
            userAccount.setCreatedAt(new SimpleDateFormat("HH:mm:ss_dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            userManagerService.save(userAccount);
        }
        Iterable<UserAccount> users = userManagerService.findAll();
        model.put("users", users);
        return "redirect:/user";
    }
}