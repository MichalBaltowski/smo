package pl.playwithme.smo.service.quiz.score;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.service.quiz.score.ScoreCalculator;

@Service
public class ZeroLevelScoreCalculator implements ScoreCalculator {

    private final UserChoice choice;
    private final int currentScore;
    private final int GOOD_CHOICE_VALUE = 10;
    private final int MEDIUM_CHOICE_VALUE = 5;

    ZeroLevelScoreCalculator(UserChoice _choice, int _currentScore) {
        choice = _choice;
        currentScore = _currentScore;
    }

    @Override
    public int calculateNewScore() {
        switch (choice) {
            case good -> {
                return GOOD_CHOICE_VALUE;
            }
            case medium -> {
                return MEDIUM_CHOICE_VALUE;
            }
            case bad -> {
                return 0;
            }
        }
        return 0;
    }
}
