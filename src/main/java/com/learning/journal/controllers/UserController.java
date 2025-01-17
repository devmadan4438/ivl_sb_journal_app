package com.learning.journal.controllers;

import com.learning.journal.entities.User;
import com.learning.journal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getById(){
        try{
            Optional<User> user = userService.getUser();
            if(user.isPresent()){
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public boolean updateUser(@RequestBody User user){
        userService.updateUser(user);
        return true;
    }

    @DeleteMapping("/{userId}")
    public boolean updateJournal(@PathVariable String userId){
        userService.remove(userId);
        return true;
    }
}
