package com.gmail.alexflanker89.lesson4.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class Question {
    private long id;
    private String body;
    private Map<Integer, String> answers;
    private int correctAnswer;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.body + ":\n");
        for (Map.Entry entry : this.answers.entrySet()) {
            sb.append(entry.getKey() + ". " + entry.getValue());
        }
        return sb.toString();
    }
}
