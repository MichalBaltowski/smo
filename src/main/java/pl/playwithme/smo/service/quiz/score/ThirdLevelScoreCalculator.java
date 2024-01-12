package pl.playwithme.smo.service.quiz.score;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.service.quiz.ResultData;
import pl.playwithme.smo.service.quiz.score.ScoreCalculator;

@Service
public class ThirdLevelScoreCalculator implements ScoreCalculator {
    private final int GOOD_CHOICE_VALUE = 25;
    private final int MEDIUM_CHOICE_VALUE = 5;
    private final int BAD_CHOICE_VALUE = 25;

    @Override
    public int calculateNewScore(ResultData data) {
        var score = data.getQuestionScore();
        switch (data.getUserChoice()) {
            case good -> {
                return score + GOOD_CHOICE_VALUE;
            }
            case medium -> {
                return score + MEDIUM_CHOICE_VALUE;
            }
            case bad -> {
                return BAD_CHOICE_VALUE;
            }
        }
        return 0;
    }
}
