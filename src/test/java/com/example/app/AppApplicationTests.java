package com.example.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.app.AppApplication;

@SpringBootTest(classes = AppApplication.class) // Aponta para a classe principal
public class AppApplicationTests {

    @Test
    void contextLoads() {
    }
}
