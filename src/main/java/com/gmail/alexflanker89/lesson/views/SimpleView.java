package com.gmail.alexflanker89.lesson.views;


import com.gmail.alexflanker89.lesson.domain.Question;
import com.gmail.alexflanker89.lesson.domain.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;

/**
 *
 */
@Component
public class SimpleView implements View {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final MessageSource messageSource;


    /**
     * Для наглядности
     * @param messageSource
     */
    @Autowired
    public SimpleView(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Override
    public void showQuestion(Question question) {
        System.out.println(question.getBody() + "?");
        for(Map.Entry entry: question.getAnswers().entrySet()){
            System.out.println(entry.getKey()+ ": " + entry.getValue());
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String ask()  {
        String res ="";
        try {
            res = reader.readLine();
        }
        catch (IOException e){
            System.out.println(e);
        }
        return res;
    }
}
