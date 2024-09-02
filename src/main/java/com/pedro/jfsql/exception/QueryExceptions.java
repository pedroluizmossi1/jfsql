package com.pedro.jfsql.exception;

import com.pedro.jfsql.util.I18n;
import com.pedro.jfsql.util.I18nMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class QueryExceptions extends RuntimeException{
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class QueryNotFoundException extends RuntimeException {
        public QueryNotFoundException(Long id) {
            super(I18n.getMessage(I18nMessages.QUERY_NOT_FOUND, String.valueOf(id)));
        }
    }

}