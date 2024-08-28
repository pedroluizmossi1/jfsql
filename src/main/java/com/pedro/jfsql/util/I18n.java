package com.pedro.jfsql.util;

import com.pedro.jfsql.handler.PropertiesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {

    private static final Logger LOGGER = LoggerFactory.getLogger(I18n.class);
    private static ResourceBundle bundle;

    static {
        initBundle();
    }

    private static void initBundle() {
        String languageTag = PropertiesHandler.getPropertiesValue("app.language");
        bundle = ResourceBundle.getBundle("jfsql", Locale.forLanguageTag(languageTag));
    }

    public static void StartTranslation() {
        LOGGER.info(getMessage(I18nMessages.STARTING_APP));
    }

    public static String getMessage(String key, String... args) {
        if (args.length > 0) {
            return String.format(bundle.getString(key), args);
        }
        return bundle.getString(key);
    }

    static void setBundle(ResourceBundle testBundle) {
        bundle = testBundle;
    }
}