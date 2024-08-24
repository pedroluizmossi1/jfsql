package com.pedro.jfsql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pedro.jfsql.util.I18n.*;
import com.pedro.jfsql.handler.DatabaseHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import static com.pedro.jfsql.util.I18n.StartTranslation;

@SpringBootApplication
public class JfsqlApplication {

    //LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHandler.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JfsqlApplication.class, args);

        //default locale
        StartTranslation();

        //create start data
        DatabaseHandler databaseHandler = context.getBean(DatabaseHandler.class);
        try {
            databaseHandler.createStartData();
        } catch (Exception e) {
            LOGGER.error("Error creating start data", e);
        }

    }

}
