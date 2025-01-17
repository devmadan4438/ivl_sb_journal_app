package com.learning.journal.controllers;

import com.learning.journal.entities.JournalEntity;
import com.learning.journal.services.JournalService;
import com.learning.journal.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    private static final Logger logger = LoggerFactory.getLogger(JournalController.class);

    @PostMapping
    public JournalEntity save(@RequestBody JournalEntity journal) {
      try{
          return journalService.saveJournal(journal);
      } catch (Exception e) {
          logger.error("Error while saving journal", e);
          throw new RuntimeException(e);
      }
    }

    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAll() {
        try{
            List<JournalEntity> allJournal = journalService.getAllJournal();
            return new ResponseEntity<>(allJournal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{journalId}")
    public ResponseEntity<?> getByJournalId(@PathVariable String journalId){
        try{
            Optional<JournalEntity> journal = journalService.getJournal(journalId);
            if(journal.isPresent()){
                return new ResponseEntity<>(journal.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{journalId}")
    public boolean getByJournalId(@PathVariable String journalId, @RequestBody JournalEntity journal){
        journalService.updateJournal(journalId,journal);
        return true;
    }

    @DeleteMapping("/{journalId}")
    public boolean updateJournal(@PathVariable String journalId){
        journalService.removeJournal(journalId);
        return true;
    }
}
