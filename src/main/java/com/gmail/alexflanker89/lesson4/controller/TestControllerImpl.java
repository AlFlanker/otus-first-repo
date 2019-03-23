package com.gmail.alexflanker89.lesson4.controller;


import com.gmail.alexflanker89.lesson4.domain.Question;
import com.gmail.alexflanker89.lesson4.domain.User;
import com.gmail.alexflanker89.lesson4.domain.UserAnswer;
import com.gmail.alexflanker89.lesson4.exceptions.UserAlreadyExistException;
import com.gmail.alexflanker89.lesson4.service.QuestionService;
import com.gmail.alexflanker89.lesson4.service.UserAnswerService;
import com.gmail.alexflanker89.lesson4.service.UserService;
import com.gmail.alexflanker89.lesson4.util.Util;
import com.gmail.alexflanker89.lesson4.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

@ShellComponent
//@Controller
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

    /**
     *
     * @param userService
     * @param userAnswerService
     * @param questionService
     * @param view
     * @param messageSource
     * Autowired для наглядности!!!!!!!!
     */
    @Autowired
    public TestControllerImpl(UserService userService, UserAnswerService userAnswerService, QuestionService questionService, View view, MessageSource messageSource) {
        this.userService = userService;
        this.userAnswerService = userAnswerService;
        this.questionService = questionService;
        this.view = view;
        this.messageSource = messageSource;
    }

    public void startAskTheQuestions(User user) {
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
    /**
     * @param locale задаем локаль
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    @ShellMethod(value = "set locale",key ="setlocal")
    public void  setLocal(@ShellOption(defaultValue = "en_US") Locale locale){
        setLocale(locale);
        view.showMessage(getLocale().toString()) ;
    }

    @ShellMethod(value = "registrate",key="reg_user")
    public void registrate(@Size(min = 2,max=40)String name, @Size(min = 2, max = 40) String lastname){
        User user =new User();
        try {
            user = new User(name, lastname);
            userService.save(user);
            startAskTheQuestions(user);
        } catch (IllegalArgumentException e) {
            view.showMessage(getMessage("incorrect"));
        } catch (UserAlreadyExistException e) {
            view.showMessage(getMessage("AlreadyExist"));
            showResult(user);
        }
    }



}
