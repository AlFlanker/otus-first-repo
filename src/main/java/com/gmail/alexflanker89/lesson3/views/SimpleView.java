package com.gmail.alexflanker89.lesson3.views;

import com.gmail.alexflanker89.lesson3.domain.Question;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 */
@Getter
@Component
public class SimpleView implements View {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
