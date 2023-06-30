package com.UniSoma.restiful.exceptions;

public class FuncionarioException extends RuntimeException {

    public FuncionarioException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FuncionarioException(String message) {
        super(message);
    }

}
