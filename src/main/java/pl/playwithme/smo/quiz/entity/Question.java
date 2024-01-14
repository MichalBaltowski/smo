package pl.playwithme.smo.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question", nullable = false, length = 150)
    private String question;

    @Lob
    @Column(name = "answer", nullable = false, length = 250)
    private String answer;

    @Column(name = "category")
    private String category;
    @Column(name = "study_score")
    private int score;

    public Question(Long id, int newScore) {
        this.id = id;
        score = newScore;
    }


    public String getIdAsString() {
        return String.valueOf(id);
    }

    public int getIDasInt() {
        return Integer.parseInt(getIdAsString());
    }
}
