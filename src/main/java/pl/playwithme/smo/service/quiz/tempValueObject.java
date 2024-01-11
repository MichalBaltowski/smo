package pl.playwithme.smo.service.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.playwithme.smo.dto.UserChoice;

@Data
@AllArgsConstructor
public class tempValueObject {
    private final int questioniD;
    private final UserChoice userChoice;
    private final int questionScore;
}
