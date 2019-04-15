package com.gmail.alexflanker89.lesson5.execptions;

public class AuthorNotExistException extends RuntimeException {
    public AuthorNotExistException(String message) {
        super(message);
    }
}
