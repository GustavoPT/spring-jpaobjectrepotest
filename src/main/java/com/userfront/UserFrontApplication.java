package com.userfront;

import com.userfront.repositories.AccountRepository;
import com.userfront.repositories.UserRepository;
//import com.userfront.repositories.UserRoleDao;
import com.userfront.models.User;
//import com.userfront.models.security.Role;
//import com.userfront.models.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserFrontApplication implements CommandLineRunner {
    // testing in the mockito
    // testing the entity manager
    // testing the crud many to many
    // thymeleaf
    // dao vs the services vs crud interfaces vs
    // classes

    // architecture


    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountService;

    public static void main(String[] args) {
        SpringApplication.run(UserFrontApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Role role = new Role();
//        role.setName("ADMIN");
//        role = roleDao.save(role);
//
//        User user = new User("test", "test", "john", "wick", "test@gmail.com", "7894561230", true);
//        user = userRepository.save(user);
//
//        UserRole userRole = new UserRole(user, role);
//        userRoleDao.save(userRole);
        // how to get something by the id
//        UserRole ur = userRoleDao.findById(L);

        accountService.deleteAccountByType("SAVING");

        // test the entity manager
        // test the tests of the application
        // how do we implement services



    }
}
