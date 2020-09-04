package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BdsDAO {
	public void create(BdsDTO dto) {
		try {
			// DB처리
			// 1. 드라이버 설정
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. 드라이버 설정 성공");
			
			// 2. DB연결
			String url = "jdbc:mysql://localhost:3366/shop";
			String user = "root";
			String password = "1234";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. DB연결 성공");
			
			// 3. SQL작성
			String sql = "INSERT INTO bds VALUES (?, ?, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql); // 얘가 있어야 sql문을 인식함 
			System.out.println("3. SQL작성 성공");
			
			// 4. SQL전달
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getWriter());
			ps.executeUpdate();
			System.out.println("4. SQL전달 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("DB에 생성처리 완료!");
	}
	
	public BdsDTO read(String id) {
		BdsDTO bag = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3366/shop";
			String user = "root";
			String password = "1234";
			Connection con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT * FROM bds WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			// SQL 전달 후 검색결과를 가지고 와야 함
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) { // true
				bag = new BdsDTO();
				String id2 = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String writer = rs.getString(4);
				
				bag.setId(id2);
				bag.setTitle(title);
				bag.setContent(content);
				bag.setWriter(writer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DB에 게시물 읽기처리 완료!");
		
		return bag;
	}
	
	public void update(String id, String title, String content) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3366/shop";
			String user = "root";
			String password = "1234";
			Connection con = DriverManager.getConnection(url, user, password);
			
			String sql = "UPDATE bds SET title = ?, content = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, id);
			
			ps.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DB에 게시물 수정처리 완료!");
	}
	
	public void delete(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3366/shop";
			String user = "root";
			String password = "1234";
			Connection con = DriverManager.getConnection(url, user, password);
			
			String sql = "DELETE FROM bds WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DB에 게시물 삭제처리 완료!");
	}
}
