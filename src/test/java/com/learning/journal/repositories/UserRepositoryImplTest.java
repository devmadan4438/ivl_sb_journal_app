package com.learning.journal.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    void findByCriteriaTest() {
        Assertions.assertNotNull(userRepository.findByCriteriaSA());
    }
}