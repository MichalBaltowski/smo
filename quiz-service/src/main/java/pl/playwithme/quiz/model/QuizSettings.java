package pl.playwithme.quiz.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class QuizSettings {

    @Id
    private String id;

    int cardLimit;

}
