package pl.playwithme.quiz.service;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.playwithme.quiz.dao.QuestionRepository;
import pl.playwithme.quiz.dao.QuizSettingsRepository;
import pl.playwithme.quiz.model.Question;
import pl.playwithme.quiz.model.QuizSettings;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {

    private final EntityManager entityManager;
    private final QuestionRepository questionRepository;
    private final QuizSettingsRepository quizSettingsRepository;

    QuizService(QuestionRepository questionRepo,
                QuizSettingsRepository quizSettingsRepo,
                EntityManager entityManager) {
        this.questionRepository = questionRepo;
        this.quizSettingsRepository = quizSettingsRepo;
        this.entityManager = entityManager;
    }

    public List<Question> getQuestionSet(QuizSettings settings) {
        return questionRepository.findAll(
                PageRequest.of(0, settings.getCardLimit())).getContent();
    }

    public List<Question> getQuestionSet(List<String> ids) {
        return questionRepository.findAllById(ids);
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public void enterNewData(Map<String, Integer> resColl) {
        resColl.forEach((questionId, newScore) -> {
            var question = questionRepository.findById(questionId).get();
            question.setScore(newScore);
            questionRepository.save(question);
        });
    }

    public List<QuizSettings> getSettings() {
        return quizSettingsRepository.findAll();
    }

    public Optional<QuizSettings> getSettings(String userId) {
        return quizSettingsRepository.findByUserId(userId);
    }

    public void addSettings(QuizSettings settings) {
        quizSettingsRepository.save(settings);
    }
}
