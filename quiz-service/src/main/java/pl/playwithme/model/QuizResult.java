package pl.playwithme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class QuizResult {

    private String questionId;
    private UserChoice userChoice;
}

