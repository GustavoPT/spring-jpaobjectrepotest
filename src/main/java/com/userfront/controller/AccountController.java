package com.userfront.controller;

import java.util.List;

import com.userfront.repositories.AccountRepository;
import com.userfront.repositories.UserRepository;
import com.userfront.models.Account;
import com.userfront.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/view")
    public String view(Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        List<Account> accounts = user.getAccounts();
        model.addAttribute("accounts", accounts);
        return "accounts";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "addAccount";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String accountPost(@ModelAttribute("account") Account account, HttpSession session) {
        // TODO:
        String username = (String) session.getAttribute("username");
        User user = userRepository.findByUsername(username);
        account.setUser(user);
        accountRepository.save(account);
        return "redirect:/account/view";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String recipientEdit(@RequestParam(value = "accountType") String accountType, Model model, HttpSession session) {
        Account account = accountRepository.findByType(accountType);
        String username = (String) session.getAttribute("username");

        List<Account> accounts = accountRepository.findAccountsByUserUsername(username);
        model.addAttribute("accounts", accounts);
        model.addAttribute("account", account);
        return "editAccount";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @Transactional
    public String recipientDelete(@RequestParam(value = "accountType") String accountType, Model model) {
        accountRepository.deleteByType(accountType);
        List<Account> accounts = accountRepository.findAccountsByUserUsername((String) model.getAttribute("username"));

        Account account = new Account();
        model.addAttribute("account", account);
        model.addAttribute("accounts", accounts);
        return "accounts";
    }
}
