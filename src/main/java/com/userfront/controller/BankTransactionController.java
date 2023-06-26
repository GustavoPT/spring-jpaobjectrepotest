package com.userfront.controller;

import com.userfront.models.*;
import com.userfront.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/bankTransaction")
public class BankTransactionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @GetMapping("/view")
    public String view(Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        List<BankTransaction> bankTransactions = user.getBankTransactions();
        model.addAttribute("bankTransactions", bankTransactions);
        return "bankTransactions";
    }

    @GetMapping("/add")
    public String add(Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        BankTransaction bankTransaction = new BankTransaction();
        List<Card> cards = user.getCards();
        List<Account> accounts = user.getAccounts();
        List<Product> products = productRepository.findAll();
        Product product = new Product();
        Merchant merchant = new Merchant();
        Account account = new Account();
        Card card = new Card();
        model.addAttribute("accounts", accounts);
        model.addAttribute("cards", cards);
        model.addAttribute("account", account);
        model.addAttribute("card", card);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        model.addAttribute("merchant", merchant);
        model.addAttribute("bankTransaction", bankTransaction);
        return "addBankTransaction";
    }

    @PostMapping("/save")
    public String bankTransactionPost(@RequestParam("card") final int cardId, @RequestParam("date") final String date, @RequestParam("amount") final Double amount, @RequestParam("id") final int id, @RequestParam("product") final String productName, @RequestParam("account") final String accountType, HttpSession session) {
        User user = userRepository.findByUsername((String) session.getAttribute("userName"));
        Card card = cardRepository.findByCode(cardId);
        Account account = accountRepository.findByType(accountType);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        Product product = productRepository.findByName(productName);
        Merchant merchant = product.getMerchant();
        if (id == 0) {
            System.out.println("add");
            BankTransaction bankTransaction = new BankTransaction();
            bankTransaction.setCard(card);
            bankTransaction.setUser(user);
            bankTransaction.setMerchant(merchant);
            bankTransaction.setAmount(amount);
            bankTransaction.setDate(date);
            bankTransaction.setProduct(product);
            bankTransaction.setAccount(account);
            bankTransactionRepository.save(bankTransaction);
        } else {
            System.out.println("edit");
            BankTransaction bankTransaction = bankTransactionRepository.findById((long) id).orElse(null);
            bankTransaction.setCard(card);
            bankTransaction.setUser(user);
            bankTransaction.setMerchant(merchant);
            bankTransaction.setAmount(amount);
            bankTransaction.setDate(date);
            bankTransaction.setProduct(product);
            bankTransaction.setAccount(account);
            bankTransactionRepository.save(bankTransaction);
        }

        return "redirect:/bankTransaction/view";
    }

    @GetMapping("/edit")
    public String bankTransactionEdit(@RequestParam(value = "bankTransactionId") int bankTransactionId, Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        BankTransaction bankTransaction = bankTransactionRepository.findById((long) bankTransactionId).orElse(null);
        List<Card> cards = user.getCards();
        List<Product> products = productRepository.findAll();
        List<Account> accounts = user.getAccounts();
        Product product = bankTransaction.getProduct();
        Merchant merchant = bankTransaction.getMerchant();
        Account account = bankTransaction.getAccount();
        model.addAttribute("account", account);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        model.addAttribute("merchant", merchant);
        model.addAttribute("accounts", accounts);
        model.addAttribute("cards", cards);
        model.addAttribute("bankTransaction", bankTransaction);

        return "editBankTransaction";
    }


    @GetMapping("/delete")
    @Transactional
    public String bankTransactionDelete(@RequestParam(value = "bankTransactionId") long bankTransactionId, Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        bankTransactionRepository.deleteById(bankTransactionId);

        List<BankTransaction> bankTransactions = bankTransactionRepository.findAll();

        BankTransaction bankTransaction = new BankTransaction();
        List<Card> cards = user.getCards();
        List<Account> accounts = user.getAccounts();
        Product product = new Product();
        Merchant merchant = new Merchant();
        Account account = new Account();
        Card card = new Card();
        model.addAttribute("account", account);
        model.addAttribute("card", card);
        model.addAttribute("product", product);
        model.addAttribute("merchant", merchant);
        model.addAttribute("accounts", accounts);
        model.addAttribute("cards", cards);
        model.addAttribute("bankTransactions", bankTransactions);
        model.addAttribute("bankTransaction", bankTransaction);
        return "bankTransactions";
    }
}

