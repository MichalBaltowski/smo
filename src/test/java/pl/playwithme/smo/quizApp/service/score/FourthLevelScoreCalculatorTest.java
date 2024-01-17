package pl.playwithme.smo.quizApp.service.score;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.quizApp.entity.UserChoice;
import pl.playwithme.smo.quizApp.service.score.ResultData;
import pl.playwithme.smo.quizApp.service.score.FourthLevelScoreCalculator;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FourthLevelScoreCalculatorTest {

    @Autowired
    private FourthLevelScoreCalculator fourthLevelScoreCalculator;

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.good, 70);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(85, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.medium, 70);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(75, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 70);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(45, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 100);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(45, newScore);
    }

}