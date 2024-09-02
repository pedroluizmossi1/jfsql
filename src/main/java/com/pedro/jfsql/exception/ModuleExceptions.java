package com.pedro.jfsql.exception;

import com.pedro.jfsql.util.I18n;
import com.pedro.jfsql.util.I18nMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ModuleExceptions extends RuntimeException{

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class ModuleNotFoundException extends RuntimeException {
        public ModuleNotFoundException(String name) {
            super(I18n.getMessage(I18nMessages.MODULE_NOT_FOUND, name));
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    public static class ModuleAlreadyExistsException extends RuntimeException {
        public ModuleAlreadyExistsException(String name) {
            super(I18n.getMessage(I18nMessages.MODULE_ALREADY_EXISTS, name));
        }
    }
}