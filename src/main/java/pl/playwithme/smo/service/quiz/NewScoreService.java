package pl.playwithme.smo.service.quiz;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.dto.QuizResult;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.service.quiz.exception.BadMatchQuestionIDException;
import pl.playwithme.smo.service.quiz.score.ScoreCalculatorFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class NewScoreService {

    private final ScoreCalculatorFactory calcFactory;
    NewScoreService(ScoreCalculatorFactory calcFactory) {
        this.calcFactory = calcFactory;
    }
    public void calculateNewScore(List<QuizResult> result, List<Question> question) {
        var resultDataList = createSomeCollection(result, question);

        for (ResultData res : resultDataList) {
            var newScore = calcFactory.getCalculator(res).calculateNewScore(res);
        }
        //calculate new score for question id
//        var newScoreCollection = Arrays.stream(someCollection).
//                map(record.id -> temp(record))
//                .collect(Collectors.toList());

        //pass new score to database for questions
        // EnterDataToDB(newScoreCollection)
    }

    public List<ResultData> createSomeCollection(List<QuizResult> result, List<Question> question) {

        if (result.size() != question.size()) {
            throw new BadMatchQuestionIDException();
        }

        List<ResultData> resulCollection = new ArrayList<>();
        var sortedResult = result
                .stream()
                .sorted(Comparator.comparing(QuizResult::getQuestionId))
                .toList();

        for (var res : sortedResult) {
            var questionId = res.getQuestionId();
            var userChoice = res.getUserChoice();
            var questionScore = question
                    .stream()
                    .filter(result1 -> result1.getId() == questionId)
                    .findFirst()
                    .get()
                    .getStudy_score();
            var newMatchedRecord = new ResultData(questionId, userChoice, questionScore);
            resulCollection.add(newMatchedRecord);
        }
        return resulCollection;
    }
}
