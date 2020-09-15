package myMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBCon;

public class HeartDAO {
	DBCon db = new DBCon();
	Connection con = db.getConnection();

	public void create(HeartDTO dto) { // 좋아요 생성
		try {
			String sql = "INSERT INTO heart VALUES (null, ?, ?) ";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getuID());
			ps.setInt(2, dto.getpNo());
			ps.executeUpdate();

			System.out.println("[HEART] INSERT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] INSERT failed.");
		}
	}

	public int read(int pNo) { // 좋아요 불러오기
		int count = 0; // 생성 및 초기화

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

	public boolean read(int pNo, String uID) { // 좋아요 중복 적용 막기 위해 기존에 눌렀는지 조회(한 게시글마다 유저별로 좋아요 최대 1회 가능)
		boolean result = false;
		try {
			String sql = "SELECT * FROM heart WHERE pNo=" + pNo + " AND uID='" + uID + "' ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

			System.out.println("[HEART] SELECT (Heart) completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[HEART] SELECT (Heart) failed.");
		}

		return result;
	}

	public void delete(HeartDTO dto) { // 좋아요 삭제
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
