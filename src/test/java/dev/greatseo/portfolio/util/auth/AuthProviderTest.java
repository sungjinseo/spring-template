package dev.greatseo.portfolio.util.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthProviderTest {
    @Autowired
    AuthProvider authProvider;

    @Test
    public void 토큰_생성하기() {
        String token = authProvider.createToken(100L,"sungjinseo", "admin");
        System.out.println(">>>>>>>>>>>>>> token = " + token);
    }

    @Test
    public void 토큰_검증하기() {
        String token = authProvider.createToken(100L,"sungjinseo", "admin");
        System.out.println(token);
        authProvider.validateToken(token);
    }
}