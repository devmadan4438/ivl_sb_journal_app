package com.learning.journal.schedular;

import com.learning.journal.entities.User;
import com.learning.journal.repositories.UserRepository;
import com.learning.journal.repositories.UserRepositoryImpl;
import com.learning.journal.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSchedular {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "* * * * * *")
    public void userSASendMail(){
        List<User> users = userRepository.findByCriteriaSA();
        for(User user : users){
            emailService.sendEmail(user.getEmail(),"Sasda","Bye");
        }
    }
}
