package com.saviobandeira.estoque.dto;

import java.util.List;
import java.util.ArrayList;
import java.time.Instant;

public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String fieldMessage) {
        errors.add(new FieldMessage(fieldName, fieldMessage));
    }
}
