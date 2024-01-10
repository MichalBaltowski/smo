package pl.playwithme.smo.service.quiz;

import pl.playwithme.smo.dto.UserChoice;

class Choice {
    private final Score questionScore;
    private final UserChoice choice;

    Choice(Score _questionScore, UserChoice _choice) {
        questionScore = _questionScore;
        choice = _choice;
    }

    int calculateNewLevel() {
        var calculator = getChoiceCalculatorForScoreLevel();
        return calculator.calculateNewScore();
    }


    private ChoiceCalculator getChoiceCalculatorForScoreLevel() {
        switch (questionScore.getQuestionLevel()) {
            case zero -> {
                return new ZeroLevelChoiceCalculator(choice, questionScore.getScore());
            }
            case first -> {
                return new FirstLevelChoiceCalculator(choice, questionScore.getScore());
            }
            case second -> {
                return new SecondLevelChoiceCalculator(choice, questionScore.getScore());
            }
            case third -> {
                return new ThirdLevelChoiceCalculator(choice, questionScore.getScore());
            }
            case fourth -> {
                return new FourthLevelChoiceCalculator(choice, questionScore.getScore());
            }
        }
        return null;
    }


}
