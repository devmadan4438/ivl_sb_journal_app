package com.learning.journal.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("users")
@Data
@Builder
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    private String email;

    private Boolean sentimentAnalysis;

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntity> journals = new ArrayList<JournalEntity>();

    private List<String> roles = new ArrayList<String>();
}
