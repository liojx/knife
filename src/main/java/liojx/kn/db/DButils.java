package liojx.kn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: liaosijun
 * @Time: 2020/12/6 16:48
 */
public class DButils {

	public static Connection getMySqlConnection() {
		String url = "jdbc:mysql://localhost:3306/dielin?characterEncoding=UTF-8&useSSL=false";
		String user = "root";
		String pwd = "hanhan";
		Connection conn= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(DButils.getMySqlConnection());
	}
}
