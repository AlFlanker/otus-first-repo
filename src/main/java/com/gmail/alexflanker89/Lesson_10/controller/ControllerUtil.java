package com.gmail.alexflanker89.Lesson_10.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

public class ControllerUtil {
    static  public Map<String, String> getErrorsMap(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream().collect(Collectors.toMap(
                        fieldError -> fieldError.getField()+"_error",
                        FieldError::getDefaultMessage
                ));
    }
}
