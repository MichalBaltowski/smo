package pl.playwithme.smo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class QuizResult {

    private int questionId;
    private UserChoice userChoice;
}

