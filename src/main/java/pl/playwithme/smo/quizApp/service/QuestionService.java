package pl.playwithme.smo.quizApp.service;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.quizApp.entity.QuizResult;
import pl.playwithme.smo.quizApp.entity.Question;

import java.util.List;

@Service
public class QuestionService {

    private final QuizService quizService;

    QuestionService(QuizService quizService) {
        this.quizService = quizService;
    }

    public List<Question> getQuestionlist(List<QuizResult> result) {
        for (var res : result) {
            System.out.println(res);
        }

        var questionIdList = result
                .stream()
                .map(res -> res.getQuestionId())
                .toList();
        var temp= quizService.getQuestionSet(questionIdList);


        System.out.println("Lista obiektów Question z bazy");
        for (var res : temp) {
            System.out.println(res);
        }

        return temp;
    }
}
