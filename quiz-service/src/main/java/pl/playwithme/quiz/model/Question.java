package pl.playwithme.quiz.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document
public class Question {

    @Id
    private String id;

    private String question;

    private String answer;

    private String category;
    private int score;

    public Question(String id, int newScore) {
        this.id = id;
        score = newScore;
    }

}
