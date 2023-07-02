package com.userfront.controller;

import com.userfront.models.User;
import com.userfront.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {

        User user = new User();

        model.addAttribute("user", user);
        return "registrationClient";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute("user") User user, Model model) {
        if (userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail()) != null) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                model.addAttribute("emailExists", true);
            }

            if (userRepository.findByUsername(user.getUsername()) != null) {
                model.addAttribute("usernameExists", true);
            }

            return "register";
        } else {
            userRepository.save(user);
            return "redirect:/bankTransactions";
        }
    }
}
