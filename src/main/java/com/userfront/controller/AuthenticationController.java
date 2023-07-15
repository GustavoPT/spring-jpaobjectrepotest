package com.userfront.controller;

// TODO: Get the login methods post and get

import com.userfront.repositories.UserRepository;
import com.userfront.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registrationclient";
    }

    @RequestMapping( method = RequestMethod.POST)
    @Transactional
    public String registerPost(
            @ModelAttribute("user") User user,
            Model model) {
        // TODO: Separate the authentication logic
        //
        if (userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail()) != null) {
            if (userRepository.findByEmail(user.getEmail()) != null) {

                System.out.println("Emial exists");
                model.addAttribute("emailExists", true);
            }
            if (userRepository.findByUsername(user.getUsername()) != null) {
                model.addAttribute("usernameExists", true);
            }
            return "register";
        } else {
            userRepository.save(user);
            return "redirect:/login";
        }
    }
    @GetMapping("/login")
    public String showLoginForm() {
        return "loginclient";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model, HttpServletRequest request) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            return "redirect:/bankTransactions/view";
        } else {
            // Authentication failed
            model.addAttribute("error", "Invalid username or password");
            return "loginclient";
        }
    }
}
