package com.learning.journal.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("journals")
@Data
@NoArgsConstructor
public class JournalEntity {

    @Id
    private String id;

    @NonNull
    private String title;

    private String description;
}
