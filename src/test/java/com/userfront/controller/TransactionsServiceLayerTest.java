package com.userfront.controller;


import com.userfront.models.*;
import com.userfront.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;
import java.util.List;

@SpringBootTest
public class TransactionsServiceLayerTest {

    // todo:
    // create -> linked to the


    // read the transactions

    //
    @Autowired
    TransactionService ts;

    @Test
    public void testTransactions() {

        Principal p = new Principal() {

            @Override
            public String getName() {
                return null;
            }
        };

        List<Transaction> transactions = ts.findTransactionList(p);

        System.out.println(transactions);

        Transaction t1 = new Transaction();
        Integer userId;
        String email ="ruvak@gmail";

        // delete
//        Mockito.when()
//        mockMVC.perform()


    }

    // create
    @Test
    public void createTransaction() {

        Account ac = new Account();
        Card c = new Card();
        Product p = new Product();
        merchant s = new merchant();

        // is there
        // a better way to approach
        // the trans
        Transaction t1 = new Transaction();

    }

    // update
    @Test
    public void updateTransaction(){



    }

}
