package pl.playwithme.smo.service.quiz.score;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.quizApp.entity.UserChoice;
import pl.playwithme.smo.quizApp.service.score.ResultData;
import pl.playwithme.smo.quizApp.service.score.ThirdLevelScoreCalculator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ThirdLevelScoreCalculatorTest {

    @Autowired
    private ThirdLevelScoreCalculator thirdLevelScoreCalculator;

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.good, 45);
        //when
        var newScore = thirdLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(70, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.medium, 45);
        //when
        var newScore = thirdLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(50, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 45);
        //when
        var newScore = thirdLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(25, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 45);
        //when
        var newScore = thirdLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(25, newScore);
    }

}