package com.github.comp354project.service;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message){
        super(message);
    }

    public DatabaseException(Throwable inner){
        super(inner);
    }

    public DatabaseException(String message, Throwable inner){
        super(message, inner);
    }
}
