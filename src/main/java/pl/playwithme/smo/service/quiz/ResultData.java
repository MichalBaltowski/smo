package pl.playwithme.smo.service.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.playwithme.smo.dto.QuestionLevel;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.service.quiz.score.*;

@Data
@AllArgsConstructor
public class ResultData {
    private final int questioniD;
    private final UserChoice userChoice;
    private final int questionScore;

    int calculateNewLevel() {
        var calculator = getChoiceCalculatorForScoreLevel();
        return calculator.calculateNewScore();
    }

    private ScoreCalculator getChoiceCalculatorForScoreLevel() {

        if (questionScore == 0) {
            return QuestionLevel.zero;
        } else if (questionScore <= 10) {
            return QuestionLevel.first;
        } else if (questionScore <= 25) {
            return QuestionLevel.second;
        } else if (questionScore <= 45) {
            return QuestionLevel.third;
        } else if (questionScore <= 100) {
            return QuestionLevel.fourth;
        } else {
            throw new RuntimeException("Bad question score, allowed value scope <0;48>");
        }


        switch (questionScore) {
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
