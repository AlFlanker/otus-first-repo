package lesson;

import com.gmail.alexflanker89.lesson1.dao.QuestionDaoSimple;

import com.gmail.alexflanker89.lesson1.service.SimpleQuestionService;
import lesson.lifeCycle.TimingExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.nio.file.Paths;
@ExtendWith(TimingExtension.class)
@DisplayName("Класс QuestionService")
public class QuestionServiceTest {


    @Test
    @DisplayName("Чтение вопросов из lesson1/test.csv")
    public void solutionReadReq() {
        QuestionDaoSimple questionDaoSimple = new QuestionDaoSimple(Paths.get("src/main/resources/lesson1/test.csv"));
        SimpleQuestionService service = new SimpleQuestionService(questionDaoSimple);

        assertEquals(6, service.getQuestions().size());

    }

    @Test()
    @DisplayName("Чтение вопросов из lesson1/question.csv")
    public void solutionReadReqMainData() {
        QuestionDaoSimple questionDaoSimple = new QuestionDaoSimple(Paths.get("src/main/resources/lesson1/question.csv"));
        SimpleQuestionService service = new SimpleQuestionService(questionDaoSimple);
        assertEquals(5,service.getQuestions().size() );


    }

}
