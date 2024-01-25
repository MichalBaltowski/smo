package pl.playwithme.quiz.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.playwithme.quiz.model.QuestionCategory;

import java.util.List;
import java.util.Optional;

public interface QuestionCategoryRepository extends MongoRepository<QuestionCategory, String> {

    Optional<List<QuestionCategory>> findAllByUserId(String userId);

}
