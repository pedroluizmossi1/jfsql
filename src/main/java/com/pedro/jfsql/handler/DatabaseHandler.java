package com.pedro.jfsql.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseHandler {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHandler.class);

    @Autowired
    public DatabaseHandler(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createStartData() throws IOException {
        try {
            if (PropertiesHandler.getPropertiesValue("db.start.data").equals("true")) {
                ClassPathResource resource = new ClassPathResource("sql/data.sql");
                String sql = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
                for (String statement : sql.split(";")) {
                    try {
                        jdbcTemplate.execute(statement.trim());
                    } catch (DuplicateKeyException e) {
                        LOGGER.error("Duplicated key:{}", e.getMessage());
                    }
                }
                PropertiesHandler.setPropertiesValue("db.start.data", "false");
                LOGGER.info("Data loaded successfully");
            }
        } catch (IOException e) {
            LOGGER.error("Error loading data.sql file", e);
        }
    }
}
