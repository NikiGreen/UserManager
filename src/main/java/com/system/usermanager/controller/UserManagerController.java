package com.system.usermanager.controller;

import com.system.usermanager.model.UserAccount;
import com.system.usermanager.model.param.Role;
import com.system.usermanager.service.UserMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public static final String USER_STATUS_ACTIVE = "ACTIVE";

    @Autowired
    private UserMangerService userManagerService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "/greeting";
    }

    @GetMapping("/contact")
    public String contact(Map<String, Object> model) {
        return "/contact";
    }

    @GetMapping("/about")
    public String about(Map<String, Object> model) {
        return "/about";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        return "/login";
    }

    @GetMapping("/user")
    public String users(Model model, Authentication authentication,
                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserAccount> page = userManagerService.findAll(pageable);
        String sessionRole = String.valueOf(authentication.getAuthorities());
        model.addAttribute("page", page);
        model.addAttribute("url", "/user");
        model.addAttribute("sessionRole", sessionRole);
        return "/users";
    }

    @PostMapping("/user/{id}")
    public String info(@PathVariable(value = "id") String id, Map<String, Object> model) {
        UserAccount userAccount = userManagerService.findById(Long.valueOf(id)).orElse(new UserAccount());
        model.put("username", userAccount.getUsername());
        model.put("firstName", userAccount.getFirstName());
        model.put("lastName", userAccount.getLastName());
        model.put("createdAt", userAccount.getCreatedAt());
        return "/profileInfo";
    }

    @GetMapping("/user/{id}/edit")
    public String getUserInfo(@PathVariable(value = "id") String id, Map<String, Object> model) {
        UserAccount userAccount = userManagerService.findById(Long.valueOf(id)).orElse(new UserAccount());
        model.put("id", userAccount.getId());
        model.put("username", userAccount.getUsername());
        model.put("password", userAccount.getPassword());
        model.put("firstName", userAccount.getFirstName());
        model.put("lastName", userAccount.getLastName());
        model.put("createdAt", userAccount.getCreatedAt());
        return "/profile";
    }

    @PostMapping("/user/{id}/edit")
    public String edit(UserAccount userAccount, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String createdAt, @RequestParam String status, @RequestParam String role, @PathVariable("id") String id, Map<String, Object> model) {

        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        if (USER_STATUS_ACTIVE.equals(status)) {
            userAccount.setActive(true);
        } else {
            userAccount.setActive(false);
        }
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setRoles(Collections.singleton(Role.valueOf(role)));
        userAccount.setCreatedAt(createdAt);
        userManagerService.save(userAccount);
        Iterable<UserAccount> users = userManagerService.findAll();
        model.put("users", users);
        return "redirect:/user";
    }

    @PostMapping("byName")
    public String byName(@RequestParam String name, Authentication authentication, Model model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserAccount> page;
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
    public String byRole(@RequestParam String role, Authentication authentication, Model model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserAccount> page;
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
    public String delete(@RequestParam String name, Authentication authentication, Map<String, Object> model) {
        Iterable<UserAccount> users;
        if (name != null && !name.isEmpty())
            userManagerService.removeAllByUsername(name);
        users = userManagerService.findAll();
        model.put("users", users);

        return "redirect:/user";
    }
}
