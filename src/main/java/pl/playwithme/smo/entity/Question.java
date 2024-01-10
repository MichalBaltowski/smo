package pl.playwithme.smo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
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

    private String category;
    private int difficulty_level;
    private int study_score;
    private LocalDate last_activation_date;

    public String getIdAsString() {
        return String.valueOf(id);
    }

    public int getIDasInt() {
        return Integer.parseInt(getIdAsString());
    }
}
