package database.TargetDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

 public class DatabaseDAO {
		protected static Connection conn;
		private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
		private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
		private static final String DB_USER = "C##";
		private static final String DB_PASS = "tosad";

		protected static Connection getConnection() throws SQLException {
			try {
				Class.forName(DB_DRIV).newInstance();
				System.out.println("connection");
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
