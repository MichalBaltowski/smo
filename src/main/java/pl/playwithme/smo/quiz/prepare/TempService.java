package pl.playwithme.smo.quiz.prepare;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.quiz.entity.QuizSettings;
import pl.playwithme.smo.quiz.repository.QuizService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempService {

    private QuizSettingsService quizSettings;
    private QuizService quizService;

    TempService(QuizSettingsService quizSettingsService,
                QuizService quizService) {
        this.quizSettings = quizSettingsService;
        this.quizService = quizService;
    }

    public List<Long> tempPrepare() {
        var quizSettings1 = quizSettings.getQuizCardLimit();
        return tempGetQuestions(quizSettings1);
    }

    public List<Long> tempGetQuestions(QuizSettings quizSettings) {
        var cardLimit = quizSettings.getCardLimit();

//        quizService.getQuestionSet()

        var temp = new ArrayList<Long>();
        temp.add(1L);
        temp.add(2L);
        return temp;
    }
}
