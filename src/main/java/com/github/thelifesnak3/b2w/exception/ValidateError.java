package com.github.thelifesnak3.b2w.exception;

import java.util.List;

public class ValidateError {
    public String message;
    public List<String> errors;

    public ValidateError(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
