package com.userfront.controller;

import com.userfront.models.BankTransaction;
import com.userfront.models.User;
import com.userfront.repositories.BankTransactionRepository;
import com.userfront.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestBankTransactionsRepository {


    @Autowired
    BankTransactionRepository btr;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testBankTransList() {

        //TODO: Get the list of
        User user = userRepository.findByUsername("da");
        List<BankTransaction> bankTransactions = user.getBankTransactions();

    }


}
