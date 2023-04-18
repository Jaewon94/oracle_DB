package day27_my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.driver.OracleDriver;

public class ProductSalesDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String url= "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##itbank";
	private String password = "it";
	private String className = OracleDriver.class.getName();
	
	public ProductSalesDAO() {
		try {
			System.out.println(className);
			Class.forName(className);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("잘못된 클래스 이름 예외");
		}
	}
	
	public ArrayList<HashMap<String, Object>> allResult() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		String sql="select P.product_name as 상품이름, sum(s.sales_total) as 매출합계\r\n" + 
				"    from sales S\r\n" + 
				"    join product P \r\n" + 
				"        on P.product_idx = S.product_idx\r\n" + 
				"        group by P.product_name";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> tmp = new HashMap<String, Object>();
				tmp.put("상품이름", rs.getString("상품이름"));
				tmp.put("매출합계", rs.getString("매출합계"));
				list.add(tmp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList<HashMap<String, Object>> registeredResult() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		String sql="select P.product_name as 상품이름, sum(s.sales_total) as 매출합계\r\n" + 
				"    from sales S\r\n" + 
				"    full outer join product P \r\n" + 
				"        on P.product_idx = S.product_idx\r\n" + 
				"        group by P.product_name";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> tmp = new HashMap<String, Object>();
				tmp.put("상품이름", rs.getString("상품이름"));
				tmp.put("매출합계", rs.getString("매출합계"));
				list.add(tmp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList<HashMap<String, Object>> unregisteredResult() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		String sql="select P.product_name as 상품이름, sum(s.sales_total) as 매출합계\r\n" + 
				"    from sales S\r\n" + 
				"    left outer join product P \r\n" + 
				"        on P.product_idx = S.product_idx\r\n" + 
				"        where s.product_idx is null\r\n" + 
				"        group by P.product_name";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> tmp = new HashMap<String, Object>();
				tmp.put("상품이름", rs.getString("상품이름"));
				tmp.put("매출합계", rs.getString("매출합계"));
				list.add(tmp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
