package com.userfront.repositories;

import com.userfront.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByUsernameAndEmail(String username, String email);

    User findByEmail(String email);

}
