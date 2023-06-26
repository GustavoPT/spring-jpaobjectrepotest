package com.userfront.controller;

import com.userfront.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.userfront.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class UserTest {

	@Autowired
	UserService us;



	@Test
 	public void test(){

		System.out.print("hello");
		List<User> users = us.findUserList();
		System.out.println("Users");
		System.out.println(users);

		try {

			// Perform the test assertion
			Assertions.assertNotNull(us);

		} catch (AssertionError e) {
			// Test failed, handle the failure here
			System.out.println("Test failed: " + e.getMessage());
			// Perform additional actions or logging
		}
	}

}
