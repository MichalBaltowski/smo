package pl.playwithme.smo.service.quiz;

import pl.playwithme.smo.dto.UserChoice;

public class ZeroLevelChoiceCalculator implements ChoiceCalculator {

    private final UserChoice choice;
    private final int currentScore;
    private final int GOOD_CHOICE_VALUE = 12;
    private final int MEDIUM_CHOICE_VALUE = 10;

    ZeroLevelChoiceCalculator(UserChoice _choice, int _currentScore) {
        choice = _choice;
        currentScore = _currentScore;
    }

    @Override
    public int calculateNewScore() {
        switch (choice) {
            case good -> {
                return GOOD_CHOICE_VALUE;
            }
            case medium -> {
                return MEDIUM_CHOICE_VALUE;
            }
            case bad -> {
                return 0;
            }
        }
        return 0;
    }
}
