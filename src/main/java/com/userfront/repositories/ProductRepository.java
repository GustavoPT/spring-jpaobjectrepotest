package com.userfront.repositories;

import com.userfront.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// REMEMBER:additional methods added to the

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByName(String productName);

    List<Product> findAll();
}
