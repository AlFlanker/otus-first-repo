package com.gmail.alexflanker89.lesson5.execptions;

public class GenreNotExistException extends RuntimeException {
    public GenreNotExistException(String message) {
        super(message);
    }
}
