package pl.playwithme.quiz.service.score;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.quiz.model.UserChoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FirstLevelScoreCalculatorTest {

    private FirstLevelScoreCalculator firstLevelScoreCalculator = new FirstLevelScoreCalculator();

    @Test
    void calcNewScoreAfterGoodChoice() {
        //given
        var resultData = new ResultData("1", UserChoice.good, 10);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(25, newScore);
    }

    @Test
    void calcNewScoreAfterMediumChoice() {
        //given
        var resultData = new ResultData("1", UserChoice.medium, 10);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(15, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice1() {
        //given
        var resultData = new ResultData("1", UserChoice.bad, 10);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }

    @Test
    void calcNewScoreAfterBadChoice2() {
        //given
        var resultData = new ResultData("1", UserChoice.bad, 24);
        //when
        var newScore = firstLevelScoreCalculator.calculateNewScore(resultData);
        //then
        assertEquals(0, newScore);
    }
}