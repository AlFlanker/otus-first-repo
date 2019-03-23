package com.gmail.alexflanker89.lesson4;

import com.gmail.alexflanker89.lesson4.lifeCycle.TimingExtension;
import com.gmail.alexflanker89.lesson4.dao.QuestionDaoSimple;
import com.gmail.alexflanker89.lesson4.service.SimpleQuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TimingExtension.class)
@DisplayName("Класс QuestionService")
public class QuestionServiceTest {

    @Test
    @DisplayName("Чтение вопросов из lesson/test.csv")
    public void solutionReadReq() {
        QuestionDaoSimple questionDaoSimple = new QuestionDaoSimple();
        //Предлагали заменить на getResourceAsStream("/lesson/test.csv"), но я же передаю path
        questionDaoSimple.setPath(Paths.get("src/test/java/resources/lesson/test.csv"));
        SimpleQuestionService service = new SimpleQuestionService(questionDaoSimple);
        assertEquals(6, service.getQuestions().size());

    }

    @Test()
    @DisplayName("Чтение вопросов из lesson/question.csv")
    public void solutionReadReqMainData() {
        QuestionDaoSimple questionDaoSimple = new QuestionDaoSimple();
        questionDaoSimple.setPath(Paths.get("src/test/java/resources/lesson/question.csv"));
        SimpleQuestionService service = new SimpleQuestionService(questionDaoSimple);
        assertEquals(5,service.getQuestions().size() );
    }

}
