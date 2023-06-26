package com.userfront.repositories;

import com.userfront.models.BankTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankTransactionRepository extends CrudRepository<BankTransaction, Long> {
    List<BankTransaction> findAll();
}
