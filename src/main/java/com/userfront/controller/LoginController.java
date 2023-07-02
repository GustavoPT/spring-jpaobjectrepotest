package com.userfront.controller;

// TODO: Get the login methods post and get

import com.userfront.repositories.UserRepository;
import com.userfront.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class LoginController {

    @Autowired
    private UserRepository us;


    @GetMapping("/login")
    public String showLoginForm() {
        return "loginclient";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        User user = us.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {

            return "redirect:/banktransactions";
        } else {
            // Authentication failed
            model.addAttribute("error", "Invalid username or password");
            return "loginclient";
        }
    }
}
