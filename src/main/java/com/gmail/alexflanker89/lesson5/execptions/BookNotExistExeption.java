package com.gmail.alexflanker89.lesson5.execptions;

public class BookNotExistExeption extends RuntimeException {
    public BookNotExistExeption(String message) {
        super(message);
    }
}
