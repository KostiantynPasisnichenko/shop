package com.project.shop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/home")
    public String index(Authentication auth, Model model) {
        String username = (auth != null && auth.isAuthenticated()) ? auth.getName() : "Гість";

        model.addAttribute("username",username);
        return "index";
    }

    @GetMapping("/gallery")
    public String gallery(Authentication auth, Model model) {
        String username = (auth != null && auth.isAuthenticated()) ? auth.getName() : "Гість";

        model.addAttribute("username",username);
        return "catalog";
    }

    @GetMapping("/login")
    public String form() {
        return "login";
    }}
