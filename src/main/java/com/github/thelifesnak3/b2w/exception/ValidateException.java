package com.github.thelifesnak3.b2w.exception;

import java.util.List;

public class ValidateException extends Throwable {
    public String message;
    public List<String> errors;

    public ValidateException(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
