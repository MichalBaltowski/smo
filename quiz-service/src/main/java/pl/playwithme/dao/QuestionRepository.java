package pl.playwithme.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.playwithme.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
