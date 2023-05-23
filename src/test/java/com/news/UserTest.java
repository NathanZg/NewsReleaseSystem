package com.news;
import com.news.entity.User;
import com.news.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    void demo(){
        User user=new User("1","2");
        userService.register(user);


    }

}
