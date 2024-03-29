package pl.playwithme.quiz.service.score;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.quiz.model.UserChoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ZeroLevelScoreCalculatorTest {

    @Autowired
    private ZeroLevelScoreCalculator zeroLevelScoreCalculator;

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData("1", UserChoice.good, 0);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(10, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData("1", UserChoice.medium, 0);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(5, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData("1", UserChoice.bad, 0);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData("1", UserChoice.bad, 9);
        //when
        var newScore = zeroLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }

}