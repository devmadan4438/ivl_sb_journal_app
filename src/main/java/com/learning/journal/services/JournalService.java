package com.learning.journal.services;

import com.learning.journal.entities.JournalEntity;
import com.learning.journal.entities.User;
import com.learning.journal.repositories.JournalRepository;
import com.learning.journal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    
    @Transactional
    public JournalEntity saveJournal(JournalEntity journal) {
       try{
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           User user = userRepository.findByUserName(authentication.getName());

           JournalEntity savedJournal = journalRepository.save(journal);

           user.getJournals().add(savedJournal);
           userService.save(user);

           return savedJournal;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    public List<JournalEntity> getAllJournal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(authentication.getName());

       return user.getJournals();
    }

    public Optional<JournalEntity> getJournal(String id) {
        return journalRepository.findById(id);
    }

    public void updateJournal(String id,JournalEntity journal) {
        journal.setId(id);
        journalRepository.save(journal);
    }

    public void removeJournal(String id) {
        journalRepository.deleteById(id);
    }
}
