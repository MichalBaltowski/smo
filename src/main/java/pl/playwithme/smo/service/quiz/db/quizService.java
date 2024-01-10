package pl.playwithme.smo.service.quiz.db;

import org.springframework.stereotype.Service;
import pl.playwithme.smo.entity.Question;
import pl.playwithme.smo.repository.QuestionRepository;

import java.util.List;

@Service
public class quizService {

    private final QuestionRepository repository;

    quizService(QuestionRepository repo) {
        repository = repo;
    }

    public List<Question> getQuestionSet() {
        return repository.findAll();
    }

    public void addQuestion(Question question) {
        repository.save(question);
    }
}
