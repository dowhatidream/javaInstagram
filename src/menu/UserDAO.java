package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import db.DBCon;

public class UserDAO {

	public void createUser(UserDTO dto) {
		try {
			DBCon db = new DBCon();
			Connection con = db.getConnection();
		
			String sql = "INSERT INTO user VALUES (?, ?, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("3. SQL 작성 성공");
			
			ps.setString(1, dto.getuID());
			ps.setString(2, dto.getuPW());
			ps.setString(3, dto.getuPhone());
			ps.setString(4, dto.getuCDate());
			
			ps.executeUpdate();
			System.out.println("4. SQL 실행 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readUser(String uID) {
		
	}
	public void updateUser(String uID, String uPW) {
		
	}
	public void deleteUser(String uID) {
		
	}
}
