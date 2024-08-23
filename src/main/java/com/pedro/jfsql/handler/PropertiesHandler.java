package com.pedro.jfsql.handler;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Setter
@Getter
@Component
public class PropertiesHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHandler.class);
    private static final String PATH = "src/main/resources/jfsqlApp.properties";
    private static Properties properties = new Properties();
    private static boolean isInitialized = false;

    public PropertiesHandler() throws FileNotFoundException {

    }

    @PostConstruct
    public void init() {
        if (!isInitialized) {
            loadProperties();
        }
    }

    private static void loadProperties() {
        try (FileInputStream in = new FileInputStream(PATH)) {
            properties.load(in);
            isInitialized = true;
        } catch (IOException e) {
            LOGGER.error("Failed to load properties from file: " + PATH, e);
        }
    }

    public static void saveProperties() throws IOException {
        try (FileOutputStream out = new FileOutputStream(PATH)) {
            properties.store(out, null);
        }
    }

    public static void setPropertiesValue(String key, String value) throws IOException {
        if (!isInitialized) {
            loadProperties();
        }
        properties.setProperty(key, value);
        saveProperties();
    }

    public static String getPropertiesValue(String key) {
        if (!isInitialized) {
            loadProperties();
        }
        return properties.getProperty(key);
    }
}