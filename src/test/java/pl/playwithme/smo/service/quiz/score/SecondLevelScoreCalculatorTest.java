package pl.playwithme.smo.service.quiz.score;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.service.quiz.ResultData;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecondLevelScoreCalculatorTest {

    @Autowired
    private SecondLevelScoreCalculator secondLevelScoreCalculator;

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData(1, UserChoice.good, 25);
        //when
        var newScore = secondLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(45, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData(1, UserChoice.medium, 25);
        //when
        var newScore = secondLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(30, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData(1, UserChoice.bad, 25);
        //when
        var newScore = secondLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(10, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData(1, UserChoice.bad, 44);
        //when
        var newScore = secondLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(10, newScore);
    }
}