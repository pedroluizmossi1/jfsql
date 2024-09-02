package com.pedro.jfsql.modules.database;

import com.pedro.jfsql.model.QueryParameter;
import jakarta.persistence.Parameter;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class DynamicQueryExecutor {

    public static List<Map<String, Object>> executeQuery(String query, Connection connection, List<QueryParameter> parameters) throws SQLException, JSQLParserException {
        List<Map<String, Object>> results = new ArrayList<>();

        if (connection == null) {
            throw new SQLException("Connection is null");
        }

        validateSelectQuery(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                QueryParameter parameter = parameters.get(i);
                preparedStatement.setObject(i + 1, parameter.getValue());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int columnCount = resultSet.getMetaData().getColumnCount();

                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = resultSet.getMetaData().getColumnName(i);
                        Object columnValue = resultSet.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    results.add(row);
                }
            }
        }
        return results;
    }

    private static void validateSelectQuery(String query) throws JSQLParserException, SQLException {
        Statement stmt = CCJSqlParserUtil.parse(query);
        if (!(stmt instanceof Select)) {
            throw new SQLException("Only SELECT queries are allowed");
        }
    }
}
