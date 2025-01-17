package com.learning.journal.services;

import com.learning.journal.entities.User;
import com.learning.journal.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = userRepository.findByUserName("MM");
        assertNotNull(user);
    }

    @ParameterizedTest
    @CsvSource({"1,2,3","2,3,4"})
    public void testAdd(int a, int b,int expected) {
        assertEquals(expected, a+b);
    }

//    @ValueSource, @EnumSource
    @ParameterizedTest
    @ArgumentsSource(ArgumentsProvider.class)
    public void testAddUser(User user){

    }
}
