package com.gmail.alexflanker89.lesson.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAnswer {
    private User user;
    private Question question;
    private int selectedAnswer;

    public UserAnswer(User user, Question question, int selectedAnswer) {
        this.user = user;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }


}
