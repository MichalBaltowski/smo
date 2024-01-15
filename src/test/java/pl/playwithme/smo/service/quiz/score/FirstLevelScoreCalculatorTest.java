package pl.playwithme.smo.service.quiz.score;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.quiz.entity.UserChoice;
import pl.playwithme.smo.quiz.service.score.ResultData;
import pl.playwithme.smo.quiz.service.score.FirstLevelScoreCalculator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FirstLevelScoreCalculatorTest {

    @Autowired
    private FirstLevelScoreCalculator firstLevelScoreCalculator;

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.good, 10);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(25, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.medium, 10);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(15, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 10);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 24);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }
}