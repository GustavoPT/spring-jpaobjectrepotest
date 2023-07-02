//package com.userfront.controller;
//
//import com.userfront.models.User;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.persistence.EntityManager;
//
//@RunWith(SpringRunner.class) // or @ExtendWith(SpringExtension.class) for JUnit 5
//@DataJpaTest
//public class EntityManagerTest {
//
//    @Autowired
//    private EntityManager entityManager;
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
////        assertThat(savedEntity.getName()).isEqualTo("Test Entity");
//    }
//}
//
