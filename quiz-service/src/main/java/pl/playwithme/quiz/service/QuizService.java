package pl.playwithme.quiz.service;

import org.hibernate.SessionFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.playwithme.quiz.dao.QuestionCategoryRepository;
import pl.playwithme.quiz.dao.QuestionRepository;
import pl.playwithme.quiz.dao.QuizSettingsRepository;
import pl.playwithme.quiz.model.Question;
import pl.playwithme.quiz.model.QuestionCategory;
import pl.playwithme.quiz.model.QuizSettings;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {

    private final SessionFactory sessionFactory;
    private final QuestionRepository questionRepository;
    private final QuizSettingsRepository quizSettingsRepository;

    private final QuestionCategoryRepository questionCategoryRepository;

    QuizService(SessionFactory sessionFactory, QuestionRepository questionRepo,
                QuizSettingsRepository quizSettingsRepo,
                QuestionCategoryRepository questionCategoryRepository) {
        this.sessionFactory = sessionFactory;
        this.questionRepository = questionRepo;
        this.quizSettingsRepository = quizSettingsRepo;
        this.questionCategoryRepository = questionCategoryRepository;
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

    public boolean ifCategoryExist(String userId, String category) {
        var session = sessionFactory.openSession();
        return true;
       // return questionCategoryRepository.exists(cat);
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

    public Optional<List<QuestionCategory>> getQuestionCategoryList(String userId) {
        return questionCategoryRepository.findAllByUserId(userId);
    }

}
