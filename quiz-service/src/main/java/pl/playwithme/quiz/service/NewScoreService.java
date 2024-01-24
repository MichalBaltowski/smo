package pl.playwithme.quiz.service;

import org.springframework.stereotype.Service;
import pl.playwithme.quiz.exception.BadMatchQuestionIDException;
import pl.playwithme.quiz.model.Question;
import pl.playwithme.quiz.model.QuizResult;
import pl.playwithme.quiz.service.score.ResultData;
import pl.playwithme.quiz.service.score.ScoreCalculatorFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewScoreService {
    private final ScoreCalculatorFactory calcFactory;

    NewScoreService(ScoreCalculatorFactory calcFactory) {
        this.calcFactory = calcFactory;
    }

    public Map<String, Integer> calculateNewScore(List<QuizResult> result, List<Question> question) {
        validateData(result, question);
        var resultDataList = createResultDataCol(result, question);
        return createNewScoreCol(resultDataList);
    }

    private List<ResultData> createResultDataCol(List<QuizResult> result, List<Question> question) {
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
                    .getScore();
            var newMatchedRecord = new ResultData(questionId, userChoice, questionScore);
            resulCollection.add(newMatchedRecord);
        }
        return resulCollection;
    }

    private void validateData(List<QuizResult> result, List<Question> question) {
        if (result.size() != question.size()) {
            throw new BadMatchQuestionIDException();
        }
    }

    private Map<String, Integer> createNewScoreCol(List<ResultData> resultDataList) {
        return resultDataList.stream()
                .collect(Collectors.toMap(
                        res -> res.getQuestioniD(),
                        res -> calcNewScore(res)
                ));
    }

    private int calcNewScore(ResultData res) {
        return calcFactory.getCalculator(res).calculateNewScore(res);
    }
}

