package pl.playwithme.smo.quizApp.service.prepare;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.quizApp.entity.QuizSettings;
import pl.playwithme.smo.quizApp.service.QuizService;

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


    public List<Long> prepareQuestionIdsList() {
        var settings = quizSettings.get();
        return getQuestionsIds(settings);
    }

    public List<Long> getQuestionsIds(QuizSettings quizSettings) {
        var questionList = quizService.getQuestionSet(quizSettings);
        return questionList
                .stream()
                .map(question -> question.getId())
                .toList();
    }
}
