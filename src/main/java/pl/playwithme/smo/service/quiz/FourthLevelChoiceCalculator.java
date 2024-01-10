package pl.playwithme.smo.service.quiz;

import pl.playwithme.smo.dto.UserChoice;

public class FourthLevelChoiceCalculator implements ChoiceCalculator {

    private final UserChoice choice;
    private final int currentScore;
    private final int GOOD_CHOICE_VALUE = 12;
    private final int MEDIUM_CHOICE_VALUE = 3;
    private final int BAD_CHOICE_VALUE = 24;

    FourthLevelChoiceCalculator(UserChoice _choice, int _currentScore) {
        choice = _choice;
        currentScore = _currentScore;
    }

    @Override
    public int calculateNewScore() {
        switch (choice) {
            case good -> {
                if (currentScore < 48) {
                    return 48;
                } else if (currentScore == 48) {
                    return 48;
                }
            }
            case medium -> {
                return currentScore + MEDIUM_CHOICE_VALUE;
            }
            case bad -> {
                return currentScore - BAD_CHOICE_VALUE;
            }
        }
        return 0;
    }


}
