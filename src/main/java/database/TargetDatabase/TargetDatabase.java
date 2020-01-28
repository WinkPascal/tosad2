package database.TargetDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TargetDatabase {
    protected static Connection conn;
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC22";
    private static final String DB_USER = "TARGET";
    private static final String DB_PASS = "TARGET";

    protected static Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_DRIV).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        return conn;
    }

    public static void closeConnection() throws SQLException {
        conn.close();
    }
}
