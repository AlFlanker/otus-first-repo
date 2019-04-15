package com.gmail.alexflanker89.lesson5.execptions;

public class CommentNotExistException extends RuntimeException {
    public CommentNotExistException(String message) {
        super(message);
    }
}
