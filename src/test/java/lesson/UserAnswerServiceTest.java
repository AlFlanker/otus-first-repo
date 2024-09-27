package lesson;

import com.gmail.alexflanker89.lesson.dao.QuestionDaoSimple;
import com.gmail.alexflanker89.lesson.dao.UserAnswerDao;
import com.gmail.alexflanker89.lesson.dao.UserAnswerDaoSimple;
import com.gmail.alexflanker89.lesson.dao.UserDaoSimple;
import com.gmail.alexflanker89.lesson.domain.User;
import com.gmail.alexflanker89.lesson.domain.UserAnswer;
import com.gmail.alexflanker89.lesson.service.SimpleQuestionService;
import com.gmail.alexflanker89.lesson.service.SimpleUserAnswerService;
import lesson.lifeCycle.TimingExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;


import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(TimingExtension.class)
@DisplayName("Класс UserAnswerServiceTest")
public class UserAnswerServiceTest {
    QuestionDaoSimple questionDaoSimple;
    SimpleQuestionService service;
    UserDaoSimple daoSimple;
    @BeforeEach
    public void load(){
         questionDaoSimple = new QuestionDaoSimple(Paths.get("src/test/java/resources/lesson/question.csv"));
         service= new SimpleQuestionService(questionDaoSimple);
         daoSimple = new UserDaoSimple();
         User u1 = new User("S","R");
         User u2 = new User("T","B");
         daoSimple.save(u1);
         daoSimple.save(u2);

    }
    @DisplayName("UserAnswerServerTest")
    @Test
    public void UserAnswerServerTest(){
        UserAnswerDao userAnswerDao = new UserAnswerDaoSimple();
        SimpleUserAnswerService answerService = new SimpleUserAnswerService(userAnswerDao);
        UserAnswer answer = new UserAnswer();
        answer.setQuestion(null);
        answer.setSelectedAnswer(0);
        answer.setUser(null);
        answerService.reqAnswer(answer);
        List<UserAnswer> allByUser = answerService.getAllByUser(null);
        assertEquals(0,allByUser.size());
        User user1 = daoSimple.findAll().get(0);
        User user2 = daoSimple.findAll().get(1);

        answer.setUser(user1);
        answer.setQuestion(service.getQuestion(1));
        answer.setSelectedAnswer(1);
        answerService.reqAnswer(answer);
        assertEquals(answer,answerService.getAllByUser(user1).get(0));

        answer = new UserAnswer();
        answer.setUser(user2);
        answer.setQuestion(service.getQuestion(1));
        answer.setSelectedAnswer(1);
        answerService.reqAnswer(answer);

        assertEquals(answer,answerService.getAllByUser(user2).get(0));


    }


}
