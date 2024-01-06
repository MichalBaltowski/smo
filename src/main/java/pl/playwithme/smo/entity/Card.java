package pl.playwithme.smo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private int id;
    private String question;
    private String answer;
    private String category;
    private int difficultyLevel;
    private int studyLevel;

    public String getId() {
        return String.valueOf(id);
    }
}
