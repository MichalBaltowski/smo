package pl.playwithme.service.prepare;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TempServiceTest {

    @Autowired
    TempService tempService;

    @Test
    void test() {
        //given
        //var res = tempService.prepareQuestionIdsList();
        //when
//        assertFalse(res.isEmpty(), "Result should not be empty");
//        assertTrue(res.stream().allMatch(item -> item instanceof Long),
//                "All items in the list should be of type Long");
    }

}