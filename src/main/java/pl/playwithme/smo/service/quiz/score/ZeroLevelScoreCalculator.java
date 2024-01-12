package pl.playwithme.smo.service.quiz.score;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.service.quiz.ResultData;

@Service
public class ZeroLevelScoreCalculator implements ScoreCalculator {

    private final int GOOD_CHOICE_VALUE = 10;
    private final int MEDIUM_CHOICE_VALUE = 5;

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
                return 0;
            }
        }
        return 0;
    }
}
