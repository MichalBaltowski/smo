package pl.playwithme.smo.quiz.prepare;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.playwithme.smo.quiz.service.prepare.TempService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TempServiceTest {

    @Autowired
    TempService tempService;

    @Test
    void test() {
        //given
        var res = tempService.prepareQuestionIdsList();
        //when
        assertFalse(res.isEmpty(), "Result should not be empty");
        assertTrue(res.stream().allMatch(item -> item instanceof Long),
                "All items in the list should be of type Long");
    }

}