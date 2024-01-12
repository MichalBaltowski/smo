package pl.playwithme.smo.service.quiz;

import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.service.quiz.score.*;

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


    private ScoreCalculator getChoiceCalculatorForScoreLevel() {
        switch (questionScore.getQuestionLevel()) {
            case zero -> {
                return new ZeroLevelScoreCalculator(choice, questionScore.getScore());
            }
            case first -> {
                return new FirstLevelScoreCalculator(choice, questionScore.getScore());
            }
            case second -> {
                return new SecondLevelScoreCalculator(choice, questionScore.getScore());
            }
            case third -> {
                return new ThirdLevelScoreCalculator(choice, questionScore.getScore());
            }
            case fourth -> {
                return new FourthLevelScoreCalculator(choice, questionScore.getScore());
            }
        }
        return null;
    }


}
