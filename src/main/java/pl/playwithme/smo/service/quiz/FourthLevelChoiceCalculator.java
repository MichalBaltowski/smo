package pl.playwithme.smo.service.quiz;

import pl.playwithme.smo.dto.UserChoice;

public class FourthLevelChoiceCalculator implements ChoiceCalculator {

    private final UserChoice choice;
    private final int currentScore;
    private final int GOOD_CHOICE_VALUE = 15;
    private final int MEDIUM_CHOICE_VALUE = 5;
    private final int BAD_CHOICE_VALUE = 45;

    FourthLevelChoiceCalculator(UserChoice _choice, int _currentScore) {
        choice = _choice;
        currentScore = _currentScore;
    }

    @Override
    public int calculateNewScore() {
        switch (choice) {
            case good -> {
                var result = currentScore + GOOD_CHOICE_VALUE;
                if (result >= 100) {
                    return 100;
                } else {
                    return  result;
                }
            }
            case medium -> {
                return currentScore + MEDIUM_CHOICE_VALUE;
            }
            case bad -> {
                return BAD_CHOICE_VALUE;
            }
        }
        return 0;
    }


}
