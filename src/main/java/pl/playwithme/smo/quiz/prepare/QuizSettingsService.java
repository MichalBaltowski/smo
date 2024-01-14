package pl.playwithme.smo.quiz.prepare;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.playwithme.smo.quiz.entity.QuizSettings;
import pl.playwithme.smo.quiz.repository.QuizService;

@Data
@Service
public class QuizSettingsService {

    private QuizService quizService;

    QuizSettingsService(QuizService quizService) {
        this.quizService = quizService;
    }

    public int getQuizCardLimit() {
        initDefaultSettingsIfTableEmpty();

        var settings = quizService.getSettings();
        System.out.println("settings z bazy: " + settings);
        return 1;
    }

    public void initDefaultSettingsIfTableEmpty() {
        var settings = quizService.getSettings();
        if (settings.isEmpty()) {
            initDefaultSettings();
        }
    }

    private void initDefaultSettings() {
        var defaultSettings = new QuizSettings();
        defaultSettings.setCardLimit(1);
        quizService.addSettings(defaultSettings);
    }

}
