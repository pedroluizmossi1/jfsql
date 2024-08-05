package com.pedro.jfsql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ModuleExceptions {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class ModuleNotFoundException extends RuntimeException {
        public ModuleNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public static class InvalidModuleDataException extends RuntimeException {
        public InvalidModuleDataException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    public static class ModuleAlreadyExistsException extends RuntimeException {
        public ModuleAlreadyExistsException(String message) {
            super(message);
        }
    }
}