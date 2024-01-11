package pl.playwithme.smo.service.quiz;

import pl.playwithme.smo.dto.UserChoice;

public class SecondLevelChoiceCalculator implements ChoiceCalculator{

    private final UserChoice choice;
    private final int currentScore;
    private final int GOOD_CHOICE_VALUE = 20;
    private final int MEDIUM_CHOICE_VALUE = 5;
    private final int BAD_CHOICE_VALUE = 10;

    SecondLevelChoiceCalculator(UserChoice _choice, int _currentScore) {
        choice = _choice;
        currentScore = _currentScore;
    }

    @Override
    public int calculateNewScore() {
        switch (choice) {
            case good -> {
                return currentScore + GOOD_CHOICE_VALUE;
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
