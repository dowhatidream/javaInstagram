package myMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBCon;

public class HeartDAO {
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	public void create(HeartDTO dto) {
		try {
			String sql = "INSERT INTO heart VALUES (null, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getuID());
			ps.setInt(2, dto.getpNo());

			ps.executeUpdate(); // ????

			System.out.println("[HEART] INSERT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] INSERT failed.");
		}
	}

	public int read(int pNo) {
		int count = 0;

		try {
			String sql = "SELECT COUNT(*) FROM heart WHERE pNo=" + pNo + " ";
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

			System.out.println("[HEART] SELECT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] SELECT failed.");
		}

		return count;
	}

	public boolean read(int pNo, String uID) {
		boolean result = false; // 좋아요 안 눌렀음
		try {
			String sql = "SELECT * FROM heart WHERE pNo=" + pNo + " AND uID='" + uID + "' ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) { // 값이 있으면
				result = true; // 이미 눌렀네요
			}

			System.out.println("[HEART] SELECT (Heart) completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] SELECT (Heart) failed.");
		}

		return result;
	}

	public void delete(HeartDTO dto) {
		try {
			String sql = "DELETE FROM heart WHERE uID=? and pNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getuID());
			ps.setInt(2, dto.getpNo());

			ps.executeUpdate();

			System.out.println("[HEART] DELETE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] DELETE failed.");
		}
	}
}
