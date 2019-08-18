package com.system.usermanager.controller;

import com.system.usermanager.model.User;
import com.system.usermanager.model.param.Role;
import com.system.usermanager.service.UserMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class UserManagerController {

    public static final String USER_STATUS_ACTIVE= "ACTIVE";

    @Autowired
    private UserMangerService userManagerService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "/greeting";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        return "/login";
    }

    @GetMapping("/user")
    public String users(Model model, Authentication authentication,
                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> page = userManagerService.findAll(pageable);
        String sessionRole = String.valueOf(authentication.getAuthorities());
        model.addAttribute("page", page);
        model.addAttribute("url", "/user");
        model.addAttribute("sessionRole", sessionRole);
        return "/users";
    }

    @PostMapping("/user/{id}")
    public String info(@PathVariable(value = "id") String id, Map<String, Object> model) {
        User user = userManagerService.findById(Long.valueOf(id)).orElse(new User());
        model.put("username", user.getUsername());
        model.put("firstname", user.getFirstName());
        model.put("lastname", user.getLastName());
        model.put("createdAt", user.getCreatedAt());
        return "/profileInfo";
    }

    @GetMapping("/user/{id}/edit")
    public String getUserInfo(@PathVariable(value = "id") String id, Map<String, Object> model) {
        User user = userManagerService.findById(Long.valueOf(id)).orElse(new User());
        model.put("username", user.getUsername());
        model.put("password", user.getPassword());
        model.put("firstName", user.getFirstName());
        model.put("lastName", user.getLastName());
        model.put("createdAt", user.getCreatedAt());
        return "/profile";
    }

    @PostMapping("/user/{id}/edit")
    public String edit(User user, @RequestParam String createdAt, @RequestParam String status, @RequestParam String role, @PathVariable("id") String id, Map<String, Object> model) {
        /*userRepository.deleteById(user.getId());*/
        /*user.setActive(Collections.singleton(Status.valueOf(status)));*/
        if (USER_STATUS_ACTIVE.equals(status)) {
            user.setActive(true);
        } else {
            user.setActive(false);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        user.setCreatedAt(createdAt);
        userManagerService.save(user);
        Iterable<User> users = userManagerService.findAll();
        model.put("users", users);
        return "redirect:/user";
    }

    @PostMapping("byName")
    public String byName(@RequestParam String name, Authentication authentication,Model model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> page;
        String sessionRole = String.valueOf(authentication.getAuthorities());
        if (!StringUtils.isEmpty(name)) {
            page = userManagerService.findAllByUsername(name, pageable);
        } else {
            page = userManagerService.findAll(pageable);
            model.addAttribute("url", "/user");
            model.addAttribute("sessionRole", sessionRole);
            model.addAttribute("page", page);
            return "/users";
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/user");
        model.addAttribute("sessionRole", sessionRole);
        return "/users";


    }

    @PostMapping("byRole")
    public String byRole(@RequestParam String role,Authentication authentication, Model model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> page;
        String sessionRole = String.valueOf(authentication.getAuthorities());
        if (!StringUtils.isEmpty(role)) {
            page = userManagerService.findAllByRoles(Collections.singleton(Role.valueOf(role)), pageable);
        } else {
            page = userManagerService.findAll(pageable);
            model.addAttribute("url", "/user");
            model.addAttribute("sessionRole", sessionRole);
            model.addAttribute("page", page);
            return "/users";
        }
        model.addAttribute("page", page);
        model.addAttribute("url", "/user");
        model.addAttribute("sessionRole", sessionRole);
        return "/users";

    }

    @PostMapping("delete")
    public String delete(@RequestParam String name,Authentication authentication, Map<String, Object> model) {
        Iterable<User> users;
        String sessionRole = String.valueOf(authentication.getAuthorities());
        if (name != null && !name.isEmpty())
            userManagerService.removeAllByUsername(name);
        users = userManagerService.findAll();
        model.put("users", users);

        return "redirect:/user";
    }
}
