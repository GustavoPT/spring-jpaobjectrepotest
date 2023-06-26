package com.userfront.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.userfront.models.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	 Account findByType(String accountType);

	 void deleteByType(String accountType);

	void deleteAccountByType(String accountType);

    List<Account> findAccountsByUserUsername(String name);
}
