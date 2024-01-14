package pl.playwithme.smo.quiz.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.playwithme.smo.dto.UserChoice;

@Data
@AllArgsConstructor
@Getter
public class ResultData {
    private final Long questioniD;
    private final UserChoice userChoice;
    private final int questionScore;
}
