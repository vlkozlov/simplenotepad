package com.vkozlov.simplenotepad.controller;

import com.vkozlov.simplenotepad.domain.Role;
import com.vkozlov.simplenotepad.domain.User;
import com.vkozlov.simplenotepad.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Map<String, Object> model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User registratedUser = userRepo.findByUsername(user.getUsername());
        if (registratedUser != null) {
            model.put("message", "User already exists!");
            return "registration";
        }

        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}