package pl.playwithme.smo.service.quiz.score;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.dto.UserChoice;
import pl.playwithme.smo.quiz.score.ResultData;
import pl.playwithme.smo.quiz.score.ZeroLevelScoreCalculator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZeroLevelScoreCalculatorTest {

    @Autowired
    private ZeroLevelScoreCalculator zeroLevelScoreCalculator;

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.good, 0);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(10, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData(1l, UserChoice.medium, 0);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(5, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 0);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData(1l, UserChoice.bad, 9);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }

}