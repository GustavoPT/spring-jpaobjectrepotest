package com.userfront.security;

import com.userfront.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;

@SpringBootTest
public class SecurityDemo {

    // test
    @Test
    public void testTheSecurityProto() {
        //
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return null;
            }
        };
        User user;




    }
}
