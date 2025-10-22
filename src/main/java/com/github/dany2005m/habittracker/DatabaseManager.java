package com.github.dany2005m.habittracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseManager {
    private static String DATABASE_URL = "jdbc:sqlite:habittrackerapp.db";

    public String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public static void setDatabaseUrl(String databaseUrl) {
        DATABASE_URL = databaseUrl;
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void initializeDatabase() {
        String habitsSql = "CREATE TABLE IF NOT EXISTS habits ("
                + " id integer PRIMARY KEY,"
                + " name text NOT NULL UNIQUE,"
                + " is_done boolean NOT NULL,"
                + " streak integer NOT NULL,"
                + " total_done integer NOT NULL"
                + ");";

        String dayCounterSql = "CREATE TABLE IF NOT EXISTS day_counter ("
                + " key text PRIMARY KEY,"
                + " value integer NOT NULL"
                + ");";

        String initialDaySql = "INSERT OR IGNORE INTO day_counter(key, value) VALUES('dayCounter', 1);";

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(habitsSql);
            statement.execute(dayCounterSql);
            statement.execute(initialDaySql);
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

}
