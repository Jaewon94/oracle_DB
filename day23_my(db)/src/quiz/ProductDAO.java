package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class ProductDAO {	// DAO (Database Access Object
	
	private Connection conn;
	private PreparedStatement pstmt;	// Statement의 subclass
	private ResultSet rs;
	
	private String url="jdbc:oracle:thin:@192.168.1.100:1521:xe";
	private String user = "c##itbank";
	private String password = "it";
	
	private String className = OracleDriver.class.getName();
	
	public ProductDAO() {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.err.println("DAO 생성자 예외 : " + e);
		}
	}
	
	public ArrayList<ProductDTO> selectAll() {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql ="select * from product order by idx";
		// conn -> pstmt -> rs -> while -> list.add(ob) -> close(열린것 전부) -> return
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			pstmt = conn.prepareStatement(sql);		// sql을 미리 넣어둔다.
			rs = pstmt.executeQuery();				// 미리 넣었으니 여기서는 sql을 지정하지 않는다.
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setExpiryDate(rs.getDate("expiryDate"));
				dto.setMemo(rs.getString("memo"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("selectAll 예외 : " + e);
		} finally {
			try { if(rs != null) rs.close(); } catch (Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if(conn != null) conn.close(); } catch (Exception e) {}
		}
		return list;
	}
	
	
	public ArrayList<ProductDTO> select(String keyword) {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		String sql ="select * from product where name like '%%%s%%'";
		sql = String.format(sql, keyword);	// printf에서 %를 출력하고 싶으면 %%를 사용한다..
		try {
			conn = DriverManager.getConnection(url,user,password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setExpiryDate(rs.getDate("expiryDate"));
				dto.setMemo(rs.getString("memo"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("select 예외 : " +e);
		}
		return list;
	}

	public int insert(ProductDTO dto) {
		int row = 0;
		String sql = "insert into product values (?,?,?,?,?)";
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIdx());				// 1번째 물음표에 idx를 설정한다. 숫자이므로 따옴표가 없다
			pstmt.setString(2, dto.getName());			// 2번째 물음표에 name을 설정한다. 문자열이므로 따옴표를 자동으로 작성해준다.
			pstmt.setInt(3, dto.getPrice());
			pstmt.setDate(4, dto.getExpiryDate());
			pstmt.setString(5, dto.getMemo());
			
			row = pstmt.executeUpdate();	// executeUpdate()는 int를 반환한다. (insert, update, delete)
											// executeQuety()는 ResultSet을 반환한다. (select)
		} catch (SQLException e) {
			System.out.println("insert 예외 : "+e);
		} finally {
			try { if(rs != null) rs.close(); } catch (Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if(conn != null) conn.close(); } catch (Exception e) {}
		}
	
		return row;
	}

	public int delete(int idx) {
		int row=0;
		String sql = "delete from product where idx = ?";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete 예외 : " +e);
		} finally {
			try { if(rs != null) rs.close(); } catch (Exception e) {}
			try { if(pstmt != null) pstmt.close(); } catch (Exception e) {}
			try { if(conn != null) conn.close(); } catch (Exception e) {}
		}
		
		
		return row;
	}
}
