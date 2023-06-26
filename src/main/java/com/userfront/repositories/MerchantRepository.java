package com.userfront.repositories;

import com.userfront.models.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
    Merchant findMerchantByName(String merchantName);
    List<Merchant> findAll();

}
