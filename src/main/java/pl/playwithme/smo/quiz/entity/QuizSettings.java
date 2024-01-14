package pl.playwithme.smo.quiz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "quiz_settings")
@Entity
@Data
public class QuizSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settings_id")
    private Long id;

    @Column(name = "card_Limit")
    int cardLimit;

}
