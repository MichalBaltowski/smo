package pl.playwithme.smo.service.quiz.score;

import pl.playwithme.smo.service.quiz.ResultData;

public interface ScoreCalculator {
    int calculateNewScore(ResultData res);
}
