package com.userfront.controller;

import com.userfront.models.Account;
import com.userfront.models.BankTransaction;
import com.userfront.models.Card;
import com.userfront.models.User;
import com.userfront.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        User user = new User();

        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {
        if (userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail()) != null) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                model.addAttribute("emailExists", true);
            }

            if (userRepository.findByUsername(user.getUsername()) != null) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {
            userRepository.save(user);
            return "redirect:/";
        }
    }

    @RequestMapping("/userFront")
    public String userFront(Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        List<Account> accounts = user.getAccounts();
        List<Card> cards = user.getCards();
        List<BankTransaction> bankTransactions = user.getTransactions();
        model.addAttribute("bankTransactions", bankTransactions);
        model.addAttribute("accounts", accounts);
        model.addAttribute("cards", cards);

        return "userFront";
    }
}
