package com.userfront.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.userfront.models.Card;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long>{

    List<Card> findAll();
    Card findByCode(int cardCode);

    void deleteByCode(int cardCode);
}
