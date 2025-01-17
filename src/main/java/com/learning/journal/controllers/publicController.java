package com.learning.journal.controllers;

import com.learning.journal.entities.User;
import com.learning.journal.services.CityService;
import com.learning.journal.services.UserService;
import com.learning.journal.util.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/public")
public class publicController {
    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody User user){
        try{
            User savedUser = userService.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/city")
    public ResponseEntity<?> getCity() {
        try {
            // Return the CityResponse object
            return ResponseEntity.ok(cityService.getCity());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong");
        }
    }
}
