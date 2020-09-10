package myMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCon;

public class CommentDAO {
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	public void create(CommentDTO dto) {
		try {
			String sql = "INSERT INTO comment VALUES (null, ?, ?, null, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getcCon());
			ps.setString(2, dto.getcCDate());
			ps.setString(3, dto.getuID());
			ps.setInt(4, dto.getpNo());

			ps.executeUpdate(); // ????

			System.out.println("[Comment] INSERT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Comment] INSERT failed.");
		}
	}

	public ArrayList<CommentDTO> read(int pNo) {
		ArrayList<CommentDTO> commentList = new ArrayList<CommentDTO>();

		try {
			String sql = "SELECT * FROM comment WHERE pNo=" + pNo + " ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) { // pNo은 게시글에서 버튼 누를때 패널 name으로 받아와야겠다!
				CommentDTO dto = new CommentDTO();
				
				int cNo = rs.getInt("cNo");
				String cCon = rs.getString("cCon");
				String cCDate = rs.getString("cCDate");
				String cUDate = rs.getString("cUDate");
				String uID = rs.getString("uID");
				
				dto.setcNo(cNo);
				dto.setcCon(cCon);
				dto.setcCDate(cCDate);
				dto.setcUDate(cUDate);
				dto.setuID(uID);

				commentList.add(dto);
			}
			
			System.out.println("[Comment] SELECT completed.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("---[Comment] SELECT failed.");
		}

		return commentList;
	}
	
	public void update(CommentDTO dto) {
		try {
			String sql = "UPDATE comment SET cCon=?, cUDate=? WHERE cNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getcCon());
			ps.setString(2, dto.getcUDate());
			ps.setInt(3, dto.getcNo());
			ps.executeUpdate();

			System.out.println("[Comment] UPDATE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Comment] UPDATE failed.");
		}
	}

	public void delete(CommentDTO dto) {
		try {
			String sql = "DELETE FROM comment WHERE cNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getcNo());
			ps.executeUpdate();

			System.out.println("[Comment] DELETE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Comment] DELETE failed.");
		}
	}
}
