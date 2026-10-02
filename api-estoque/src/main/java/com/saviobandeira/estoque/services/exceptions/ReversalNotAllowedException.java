package com.saviobandeira.estoque.services.exceptions;

public class ReversalNotAllowedException extends RuntimeException {

    public ReversalNotAllowedException(String message) {
        super(message);
    }
}
