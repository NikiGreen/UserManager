package com.system.usermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserManagerController {

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
}
