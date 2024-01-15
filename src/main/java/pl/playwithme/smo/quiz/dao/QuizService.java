package pl.playwithme.smo.quiz.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import pl.playwithme.smo.quiz.entity.Question;
import pl.playwithme.smo.quiz.entity.QuizSettings;

import java.util.List;
import java.util.Map;

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
        Session session = entityManager.unwrap(Session.class);
        Query<Question> query = session.createQuery("FROM Question", Question.class);
        query.setMaxResults(settings.getCardLimit());
        return query.getResultList();
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
