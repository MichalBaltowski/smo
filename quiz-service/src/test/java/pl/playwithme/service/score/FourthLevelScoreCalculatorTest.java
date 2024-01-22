package pl.playwithme.service.score;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.model.UserChoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FourthLevelScoreCalculatorTest {

    @Autowired
    private FourthLevelScoreCalculator fourthLevelScoreCalculator;

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData("1", UserChoice.good, 70);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(85, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData("1", UserChoice.medium, 70);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(75, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData("1", UserChoice.bad, 70);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(45, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData("1", UserChoice.bad, 100);
        //when
        var newScore = fourthLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(45, newScore);
    }

}