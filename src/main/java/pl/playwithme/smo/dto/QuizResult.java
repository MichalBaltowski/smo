package pl.playwithme.smo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizResult {

    private int questionId;
    private UserChoice userChoice;
}
