package pl.playwithme.quiz.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.quiz.model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
}
