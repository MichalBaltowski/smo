package pl.playwithme.quiz.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class QuestionCategory {

    @Id
    String id;
    String userId;
    String name;
}
