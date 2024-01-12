package pl.playwithme.smo.service.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.playwithme.smo.dto.UserChoice;

@Data
@AllArgsConstructor
@Getter
public class ResultData {
    private final int questioniD;
    private final UserChoice userChoice;
    private final int questionScore;
}
