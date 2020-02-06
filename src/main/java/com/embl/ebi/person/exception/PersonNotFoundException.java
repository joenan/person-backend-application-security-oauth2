package com.embl.ebi.person.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message, Long id) {
        super(message + ": " +id);
    }
}
