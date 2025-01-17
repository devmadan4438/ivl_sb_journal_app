package com.learning.journal.services;

import com.learning.journal.entities.User;
import com.learning.journal.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public User save(User user) {
        try{
            user.setPassword(passwordEncoder.encode((user.getPassword())));
            user.setRoles(Arrays.asList("USER"));
            return  userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<User> getAll() {
       return userRepository.findAll();
    }

    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User savedUser = userRepository.findByUserName(authentication.getName());

        if(savedUser != null) {
            return Optional.ofNullable(userRepository.findByUserName(savedUser.getUserName()));
        }
        return Optional.empty();
    }

    public void updateUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User savedUser = userRepository.findByUserName(authentication.getName());

        if(savedUser != null) {
            savedUser.setUserName(user.getUserName());
            savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(savedUser);
        }
    }

    public void remove(String id) {
        userRepository.deleteById(id);
    }

}
