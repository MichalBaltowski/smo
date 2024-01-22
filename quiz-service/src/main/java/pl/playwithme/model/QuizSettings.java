package pl.playwithme.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class QuizSettings {

    @Id
    private String id;

    int cardLimit;

}
