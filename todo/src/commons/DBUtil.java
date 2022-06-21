package commons;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getConnection(String url, String ser, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, ser, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
