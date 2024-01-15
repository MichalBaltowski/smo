package pl.playwithme.smo.service.quiz.score;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.quizApp.entity.UserChoice;
import pl.playwithme.smo.quizApp.service.score.*;
import pl.playwithme.smo.quizApp.score.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreCalculatorFactoryTest {

    @Autowired
    private ScoreCalculatorFactory factory;

    @Test
    void getZeroLevelCalculatorTest() {
        //given
        var res = new ResultData(1l, UserChoice.good, 0);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(ZeroLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getZeroLevelCalculatorTest2() {
        //given
        var res = new ResultData(1l, UserChoice.good, 9);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(ZeroLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getFirstLevelCalculatorTest() {
        //given
        var res = new ResultData(1l, UserChoice.good, 10);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(FirstLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getFirstLevelCalculatorTest2() {
        //given
        var res = new ResultData(1l, UserChoice.good, 24);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(FirstLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getSecondLevelCalculatorTest() {
        //given
        var res = new ResultData(1l, UserChoice.good, 25);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(SecondLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getSecondLevelCalculatorTest2() {
        //given
        var res = new ResultData(1l, UserChoice.good, 44);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(SecondLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getThirdLevelCalculatorTest() {
        //given
        var res = new ResultData(1l, UserChoice.good, 45);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(ThirdLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getThirdLevelCalculatorTest2() {
        //given
        var res = new ResultData(1l, UserChoice.good, 69);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(ThirdLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getFourthLevelCalculatorTest() {
        //given
        var res = new ResultData(1l, UserChoice.good, 70);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(FourthLevelScoreCalculator.class, calc.getClass());
    }

    @Test
    void getFourthLevelCalculatorTest2() {
        //given
        var res = new ResultData(1l, UserChoice.good, 100);
        //when
        var calc = factory.getCalculator(res);
        //then
        assertEquals(FourthLevelScoreCalculator.class, calc.getClass());
    }

}