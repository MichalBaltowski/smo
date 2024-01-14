package pl.playwithme.smo.quiz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.smo.quiz.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
