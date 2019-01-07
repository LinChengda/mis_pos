package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 连接数据库的工具
 * @author 林成大
 */
public class JdbcUtils {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/pos";
	private static String user = "root";
	private static String password = "root";
	
	static{
		try {
			Class.forName(driver);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 获得连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws  SQLException{
		// 获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void closeResource(Connection conn , Statement st , ResultSet rs){
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
			}
		}

		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		
	}

}
