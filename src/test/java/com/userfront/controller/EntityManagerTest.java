package com.userfront.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

//
import com.userfront.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import com.userfront.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringJUnitConfig
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
////public class EntityManagerTest {
////
////    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    public void testEntityManager() {
//        // Perform your tests using the EntityManager
//        // For example, you can persist an entity and retrieve it
//        User entity = new User();
//        entity.setUsername("Test Entity");
//        entityManager.persist(entity);
//        entityManager.flush();
//
//        User savedEntity = entityManager.find(User.class, entity.getUserId());
//        assertThat(savedEntity.getUsername()).isEqualTo("Test Entity");
//    }
//}
//
//
