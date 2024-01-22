package pl.playwithme.service.prepare;

import org.springframework.stereotype.Service;
import pl.playwithme.model.QuizSettings;
import pl.playwithme.service.QuizService;

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


    public List<String> prepareQuestionIdsList() {
        var settings = quizSettings.get();
        return getQuestionsIds(settings);
    }

    public List<String> getQuestionsIds(QuizSettings quizSettings) {
        var questionList = quizService.getQuestionSet(quizSettings);
        return questionList
                .stream()
                .map(question -> question.getId())
                .toList();
    }
}
