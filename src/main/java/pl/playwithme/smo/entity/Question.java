package pl.playwithme.smo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Entity
@Data
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", nullable = false)
    private Long id;

    @Column(name = "question", nullable = false, length = 150)
    private String question;

    @Lob
    @Column(name = "answer", nullable = false, length = 250)
    private String answer;

    private String category;
    private int difficulty_level;
    private int study_level;
    private Date last_activation_date;

    public String getId() {
        return String.valueOf(id);
    }
}
