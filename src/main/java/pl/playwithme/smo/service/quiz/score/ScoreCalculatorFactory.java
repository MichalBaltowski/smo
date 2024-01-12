package pl.playwithme.smo.service.quiz.score;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.service.quiz.*;

@Service
public final class ScoreCalculatorFactory {


    private final ZeroLevelScoreCalculator zeroCalc;
    private final FirstLevelScoreCalculator firstCalc;
    private final SecondLevelScoreCalculator secondCalc;
    private final ThirdLevelScoreCalculator thirdCalc;
    private final FourthLevelScoreCalculator fourthCalc;


    ScoreCalculatorFactory(ZeroLevelScoreCalculator zeroCalc,
                           FirstLevelScoreCalculator firstCalc,
                           SecondLevelScoreCalculator secondCalc,
                           ThirdLevelScoreCalculator thirdCalc,
                           FourthLevelScoreCalculator fourthCalc
    ) {
        this.zeroCalc = zeroCalc;
        this.firstCalc = firstCalc;
        this.secondCalc = secondCalc;
        this.thirdCalc = thirdCalc;
        this.fourthCalc = fourthCalc;
    }

    public ScoreCalculator getCalculator(ResultData res) {
        var score = res.getQuestionScore();
        if (score <= 9) {
            return zeroCalc;
        } else if (score <= 24) {
            return firstCalc;
        } else if (score <= 44) {
            return secondCalc;
        } else if (score <= 69) {
            return thirdCalc;
        } else if (score <= 100) {
            return fourthCalc;
        } else {
            throw new RuntimeException("Bad question score, allowed value scope <0;48>");
        }
    }

}
