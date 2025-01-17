package com.learning.journal.repositories;

import com.learning.journal.entities.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntity, String> {

}
