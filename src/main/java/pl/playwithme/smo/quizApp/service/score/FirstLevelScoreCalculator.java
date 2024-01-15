package pl.playwithme.smo.quizApp.service.score;

import org.springframework.stereotype.Service;

@Service
public class FirstLevelScoreCalculator implements ScoreCalculator {

    private final int GOOD_CHOICE_VALUE = 15;
    private final int MEDIUM_CHOICE_VALUE = 5;
    private final int BAD_CHOICE_VALUE = 0;


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
