package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String sql;
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String className = OracleDriver.class.getName();
		String url = "jdbc:oracle:thin:@192.168.1.100:1521:xe";
		String user = "c##itbank";
		String password = "it";
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		
		System.out.print("SQL > ");
		sql = sc.nextLine();
		
		Class.forName(className);
		conn = DriverManager.getConnection(url,user, password);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			MemberDTO ob = new MemberDTO();
			ob.setName(rs.getString("name"));	// DB의 name을 문자열로 ob에 세팅한다.
			ob.setAge(rs.getInt("age"));		// DB의 age를 정수로 ob에 세팅한다.
			list.add(ob);
		}
		sc.close();
		rs.close();
		stmt.close();
		conn.close();
		
		list.forEach(member -> {
			System.out.printf("name : %s, age : %d살\n",member.getName(),member.getAge());
		});
	}
}
