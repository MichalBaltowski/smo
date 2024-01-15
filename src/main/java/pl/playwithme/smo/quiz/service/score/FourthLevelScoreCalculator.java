package pl.playwithme.smo.quiz.service.score;

import org.springframework.stereotype.Service;

@Service
public class FourthLevelScoreCalculator implements ScoreCalculator {
    private final int GOOD_CHOICE_VALUE = 15;
    private final int MEDIUM_CHOICE_VALUE = 5;
    private final int BAD_CHOICE_VALUE = 45;

    @Override
    public int calculateNewScore(ResultData data) {
        var score = data.getQuestionScore();
        switch (data.getUserChoice()) {
            case good -> {
                var result = score + GOOD_CHOICE_VALUE;
                if (result >= 100) {
                    return 100;
                } else {
                    return result;
                }
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
