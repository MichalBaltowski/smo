package pl.playwithme.service.score;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.playwithme.entity.UserChoice;

@Data
@AllArgsConstructor
@Getter
public class ResultData {
    private final Long questioniD;
    private final UserChoice userChoice;
    private final int questionScore;
}
