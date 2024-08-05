package com.pedro.jfsql.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pedro.jfsql.handler.PropertiesHandler;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class I18nTest {

    @BeforeEach
    void setUp() {
        String languageTag = PropertiesHandler.getPropertiesValue("app.language");
        ResourceBundle testBundle = ResourceBundle.getBundle("jfsql", Locale.forLanguageTag(languageTag));
        I18n.setBundle(testBundle);
    }

    @Test
    void getMessage() {
        String message = I18n.getMessage(I18nMessages.STARTING_APP);
        assertNotNull(message, "The message for STARTING_APP should not be null");
        assertEquals("Starting application...", message);
        assertEquals("Application language en-US", I18n.getMessage(I18nMessages.APP_LANGUAGE, "en-US"));
    }
}