package pl.playwithme.smo.quiz.repository;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.playwithme.smo.quiz.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    private final QuestionRepository repository;

    QuizService(QuestionRepository repo) {
        repository = repo;
    }

    public List<Question> getQuestionSet() {
        //return repository.findAll();
        var temp = new ArrayList<Long>();
        temp.add(1L);
        temp.add(2L);
        return repository.findAllById(temp);
    }

    public List<Question> getQuestionSet(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public void addQuestion(Question question) {
        repository.save(question);
    }

    public void enterNewData(Map<Long, Integer> resColl) {
        resColl.forEach((questionId, newScore) -> {
            var question = repository.findById(questionId).get();
            question.setScore(newScore);
            repository.save(question);
        });

    }
}
