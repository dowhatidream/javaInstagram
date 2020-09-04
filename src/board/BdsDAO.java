package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BdsDAO {
	public void create(BdsDTO dto) {
		try {
			// DBó��
			// 1. ����̹� ����
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("1. ����̹� ���� ����");
			
			// 2. DB����
			String url = "jdbc:mysql://localhost:3366/shop";
			String user = "root";
			String password = "1234";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2. DB���� ����");
			
			// 3. SQL�ۼ�
			String sql = "INSERT INTO bds VALUES (?, ?, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql); // �갡 �־�� sql���� �ν��� 
			System.out.println("3. SQL�ۼ� ����");
			
			// 4. SQL����
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			ps.setString(4, dto.getWriter());
			ps.executeUpdate();
			System.out.println("4. SQL���� ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("DB�� ����ó�� �Ϸ�!");
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
			
			// SQL ���� �� �˻������ ������ �;� ��
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
		System.out.println("DB�� �Խù� �б�ó�� �Ϸ�!");
		
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
		System.out.println("DB�� �Խù� ����ó�� �Ϸ�!");
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
		System.out.println("DB�� �Խù� ����ó�� �Ϸ�!");
	}
}
