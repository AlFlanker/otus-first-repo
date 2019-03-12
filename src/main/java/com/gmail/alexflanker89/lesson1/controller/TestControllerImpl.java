package com.gmail.alexflanker89.lesson1.controller;

import com.gmail.alexflanker89.lesson1.domain.Question;
import com.gmail.alexflanker89.lesson1.domain.User;
import com.gmail.alexflanker89.lesson1.domain.UserAnswer;
import com.gmail.alexflanker89.lesson1.service.QuestionService;
import com.gmail.alexflanker89.lesson1.service.UserAnswerService;
import com.gmail.alexflanker89.lesson1.service.UserService;
import com.gmail.alexflanker89.lesson1.util.Util;
import com.gmail.alexflanker89.lesson1.views.View;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class TestControllerImpl implements TestController {
    private final UserService userService;
    private final UserAnswerService userAnswerService;
    private final QuestionService questionService;
    private final View view;

    public TestControllerImpl(UserService userService, UserAnswerService userAnswerService, QuestionService questionService, View view) {
        this.userService = userService;
        this.userAnswerService = userAnswerService;
        this.questionService = questionService;
        this.view = view;
    }

    public void startTest() {
        view.showMessage("Введите имя и фамилию(через пробел)");
        String line = view.ask();
        while (!Util.checkUserData(line)) {
            view.showMessage("Некорректный ввод");
            line = view.ask();
        }
        String res[] = line.split(" ");
        User u = new User(res[0], res[1]);
        try {
            userService.save(u);
            startAskTheQuestions(u);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            view.showMessage("Уже сдавал");
            showResult(u);
        }
    }

    public void startAskTheQuestions(User user) {

        for (Question question : questionService.getQuestions()) {
            view.showQuestion(question);
            int answer = Util.getNumAnswer(view.ask());
            while (answer <= 0 || answer > question.getAnswers().size()) {
                view.showMessage("повторите ввод");
                answer = Util.getNumAnswer(view.ask());
            }
            userAnswerService.reqAnswer(new UserAnswer(user, question, answer));
        }
        showResult(user);
    }

    public void showResult(User user) {
        List<UserAnswer> all = userAnswerService.getAllByUser(user);
        for (UserAnswer answer : all) {
            view.showMessage(answer.toString());
        }
    }
}
