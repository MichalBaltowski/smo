package pl.playwithme.smo.quiz.repository;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.quiz.entity.Question;
import pl.playwithme.smo.quiz.entity.QuizSettings;

import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizSettingsRepository quizSettingsRepository;

    QuizService(QuestionRepository questionRepo,
                QuizSettingsRepository quizSettingsRepo) {
        questionRepository = questionRepo;
        quizSettingsRepository = quizSettingsRepo;
    }

    public List<Question> getQuestionSet() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionSet(List<Long> ids) {
        return questionRepository.findAllById(ids);
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public void enterNewData(Map<Long, Integer> resColl) {
        resColl.forEach((questionId, newScore) -> {
            var question = questionRepository.findById(questionId).get();
            question.setScore(newScore);
            questionRepository.save(question);
        });
    }

    public List<QuizSettings> getSettings() {
        return quizSettingsRepository.findAll();
    }

    public void addSettings(QuizSettings settings) {
        quizSettingsRepository.save(settings);
    }
}
