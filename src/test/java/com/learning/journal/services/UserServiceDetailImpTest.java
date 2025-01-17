package com.learning.journal.services;

import com.learning.journal.entities.User;
import com.learning.journal.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class UserServiceDetailImpTest {
    @InjectMocks
    private UserDetailsServiceImp userDetailsServiceImp;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(anyString())).thenReturn(User.builder().userName("MM").password("261872").roles(new ArrayList<>()).build());
        Assertions.assertNotNull(userDetailsServiceImp.loadUserByUsername("MM"));
    }
}
