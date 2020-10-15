package com.multi;

import com.multi.controller.MainController;
import com.multi.mysql.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MainTest {

    @Autowired
    private MainController mainController;

    @Test
    public void testGetAllUsers() {
        List<User> users = mainController.getAlluser();
        Assertions.assertEquals(1, users.size());
    }
}
