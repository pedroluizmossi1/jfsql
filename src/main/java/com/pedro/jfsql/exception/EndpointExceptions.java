package com.pedro.jfsql.exception;

import com.pedro.jfsql.util.I18n;
import com.pedro.jfsql.util.I18nMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

public class EndpointExceptions extends RuntimeException{
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class EndpointNotFoundException extends RuntimeException {
        public EndpointNotFoundException(String path, Long id) {
            super(I18n.getMessage(I18nMessages.ENDPOINT_NOT_FOUND, Optional.ofNullable(path).orElse(id.toString())));
        }
    }


}