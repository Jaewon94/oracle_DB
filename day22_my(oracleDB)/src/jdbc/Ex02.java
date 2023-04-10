package jdbc;

import java.sql.*;
import java.text.SimpleDateFormat; 

public class Ex02 {
	public static void main(String[] args) throws Exception {
		String sql = "select sysdba from dual";
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:oracle:thin:@192.168.1.100:1521:xe";
		String user = "c##itbank";
		String password = "it";
		Date today = null;
		
		Class.forName(driverName);
		
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			today = rs.getDate(0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 dd일 a hh시 mm분 ss초");
		String result =sdf.format(today);
		long ln =today.getTime();
		System.out.println(ln);
		System.out.println(result);
		
		rs.close();
		stmt.close();
		conn.close();
	}
}
