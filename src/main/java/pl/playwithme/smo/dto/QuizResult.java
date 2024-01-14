package pl.playwithme.smo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class QuizResult {

    private Long questionId;
    private UserChoice userChoice;
}

