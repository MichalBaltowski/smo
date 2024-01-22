package pl.playwithme.service.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.playwithme.model.UserChoice;

@Data
@AllArgsConstructor
@Getter
public class ResultData {
    private final String questioniD;
    private final UserChoice userChoice;
    private final int questionScore;
}
