package com.gmail.alexflanker89.lesson.controller;

import com.gmail.alexflanker89.lesson.domain.Question;
import com.gmail.alexflanker89.lesson.domain.User;
import com.gmail.alexflanker89.lesson.domain.UserAnswer;
import com.gmail.alexflanker89.lesson.exceptions.UserAlreadyExistException;
import com.gmail.alexflanker89.lesson.service.QuestionService;
import com.gmail.alexflanker89.lesson.service.UserAnswerService;
import com.gmail.alexflanker89.lesson.service.UserService;
import com.gmail.alexflanker89.lesson.util.Util;
import com.gmail.alexflanker89.lesson.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

@Controller
@PropertySource("/application.yml")
public class TestControllerImpl implements TestController {

    private final UserService userService;
    private final UserAnswerService userAnswerService;
    private final QuestionService questionService;
    private final View view;
    private final MessageSource messageSource;
    @Value("${url}")
    private Path path;
    @Value("#{systemProperties['user.region']}")
    private Locale locale;



    @Autowired
    public TestControllerImpl(UserService userService, UserAnswerService userAnswerService, QuestionService questionService, View view, MessageSource messageSource) {
        this.userService = userService;
        this.userAnswerService = userAnswerService;
        this.questionService = questionService;
        this.view = view;
        this.messageSource = messageSource;
    }

    public void startTest() {
        locale = new Locale("en","US");
        while(true) {
            view.showMessage(getMessage("greeting"));
            String line = view.ask();
            while (!Util.checkUserData(line)) {
                view.showMessage(getMessage("incorrect"));
                line = view.ask();
            }
            String res[] = line.split(" ");
            User u = new User(res[0], res[1]);
            try {
                userService.save(u);
                startAskTheQuestions(u);
            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
                view.showMessage(getMessage("incorrect"));
            } catch (UserAlreadyExistException e) {
//                System.out.println(e.getMessage());
                view.showMessage(getMessage("AlreadyExist"));
                showResult(u);
            }
        }
    }

    private void startAskTheQuestions(User user) {
        questionService.setPath(this.path);
        for (Question question : questionService.getQuestions()) {
            view.showQuestion(question);
            int answer = Util.getNumAnswer(view.ask());
            while (answer <= 0 || answer > question.getAnswers().size()) {
                view.showMessage(getMessage("repeat"));
                answer = Util.getNumAnswer(view.ask());
            }
            userAnswerService.reqAnswer(new UserAnswer(user, question, answer));
        }
        showResult(user);
    }

    public void showResult(User user) {
        List<UserAnswer> all = userAnswerService.getAllByUser(user);
        for (UserAnswer answer : all) {
            showAnswer(answer);
        }
    }


    private void showAnswer(UserAnswer answer) {
        view.showMessage(answer.getUser().getUsername() + " " + answer.getUser().getLastName() + "\n" +
                answer.getQuestion().getBody() + " ?\n" +
                getMessage("chose")+" = " + answer.getQuestion().getAnswers().get(answer.getSelectedAnswer()) + "\n" +
                getMessage("correct")+" = " + (answer.getSelectedAnswer() == (answer.getQuestion().getCorrectAnswer() + 1)));
    }

    private String getMessage(String message){
        return messageSource.getMessage(message,null,locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
