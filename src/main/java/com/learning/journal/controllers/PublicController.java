package com.learning.journal.controllers;

import com.learning.journal.entities.User;
import com.learning.journal.services.CityService;
import com.learning.journal.services.UserDetailsServiceImp;
import com.learning.journal.services.UserService;
import com.learning.journal.services.rabbitmq.RabbitProducer;
import com.learning.journal.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RabbitProducer rabbitProducer;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            User savedUser = userService.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

            UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return  new ResponseEntity<>(jwt,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while authentication",e);
            return new ResponseEntity<>("Incorrect username and password", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/city")
    public ResponseEntity<?> getCity() {
        try {
            // rabbitProducer.sendEmailToQueue("Test message");
            // Return the CityResponse object
            return ResponseEntity.ok(cityService.getCity());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong");
        }
    }
}
