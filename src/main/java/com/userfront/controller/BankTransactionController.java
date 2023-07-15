package com.userfront.controller;

import com.userfront.models.*;
import com.userfront.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


enum Account {


}
@Controller
@RequestMapping("/bankTransactions")
public class BankTransactionController {

//    @PersistenceContext
//    private EntityManager entityManager;
    @Autowired
    private JpaRepository<Object, Long> repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    // TODO: Apply sorting algorithms
    @GetMapping("/view")
    public String view(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        User userWithFullInformation = userRepository.findByUsername(user.getUsername());
        List<BankTransaction> bankTransactions = userWithFullInformation.getBankTransactions();
        model.addAttribute("bankTransactions", bankTransactions);
        return "bankTransactions/bankTransactions";
    }

    @GetMapping("/add")
    public String add(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        User userWithFullInformation = userRepository.findByUsername(user.getUsername());
        BankTransaction bankTransaction = new BankTransaction();
        List<Card> cards = userWithFullInformation.getCards();

        model.addAttribute("cards", cards);
        model.addAttribute("bankTransaction", bankTransaction);
        return "bankTransactions/addTransaction";
    }
    //TODO: Create a sortiing algorihtm
    // either iin the fronend or in the backend
    // decide if
    @PostMapping("/save")
    public String bankTransactionPost(
            @RequestParam("card") final String cardId
            , @RequestParam("date") final String date,
            @RequestParam("amount") final Double amount
            , @RequestParam("id") final String id,
            @RequestParam("product")
            final String productName,
            @RequestParam("account")
            final String accountType,
            HttpSession session) {
        User user = userRepository.findByUsername((String) session.getAttribute("userName"));
        Card card = cardRepository.findByCode(Integer.valueOf(cardId));

        if (id.equals("")) {
            System.out.println("add");
            BankTransaction bankTransaction =
                    new BankTransaction();
            bankTransactionRepository.save(bankTransaction);
        } else {
            System.out.println("edit");
            BankTransaction bankTransaction = bankTransactionRepository.findById(Long.valueOf(id)).orElse(null);

           bankTransactionRepository.save(bankTransaction);
        }

        return "redirect:/bankTransactions/view";
    }

    @GetMapping("/edit")
    public String bankTransactionEdit(@RequestParam(value = "bankTransactionId") int bankTransactionId, Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        BankTransaction bankTransaction = bankTransactionRepository.findById((long) bankTransactionId).orElse(null);
        List<Card> cards = user.getCards();
        model.addAttribute("cards", cards);
        model.addAttribute("bankTransaction", bankTransaction);

        return "bankTransactions/editBankTransaction";
    }


    @GetMapping("/delete")
    @Transactional
    public String bankTransactionDelete(@RequestParam(value = "bankTransactionId") long bankTransactionId, Model model) {
        User user = userRepository.findByUsername((String) model.getAttribute("userName"));
        bankTransactionRepository.deleteById(bankTransactionId);

        List<BankTransaction> bankTransactions = bankTransactionRepository.findAll();

        BankTransaction bankTransaction = new BankTransaction();
        List<Card> cards = user.getCards();

        model.addAttribute("cards", cards);
        model.addAttribute("bankTransactions", bankTransactions);
        return "bankTransactions/bankTransactions";
    }
}

