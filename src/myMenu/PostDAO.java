package myMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PostDAO {
	static DBCon db = new DBCon();
	static Connection con = db.getConnection();

	public void create(PostDTO dto) {
		try {
			String sql = "INSERT INTO post VALUES (null, ?, ?, ?, null, 0, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, dto.getpImg());
			ps.setString(2, dto.getpCon());
			ps.setString(3, dto.getpCDate());
			ps.setString(4, dto.getuID());
			ps.executeUpdate();
			System.out.println("[Post] INSERT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] INSERT failed.");
		}
	}

	public ArrayList<String> read() {
		PostDTO dto = new PostDTO();
		ArrayList<String> postList = new ArrayList<String>();

		try {
			String sql = "SELECT * FROM post ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int pNo = rs.getInt("pNo");
				String pImg = rs.getString("pImg");
				String pCon = rs.getString("pCon");
				int pLike = rs.getInt("pLike");
				String pCDate = rs.getString("pCDate");
				String uID = rs.getString("uID");

				dto.setpNo(pNo);
				dto.setpImg(pImg);
				dto.setpCon(pCon);
				dto.setpLike(pLike);
				dto.setpCDate(pCDate);
				dto.setuID(uID);

				postList.add(String.valueOf(dto));
			}

			System.out.println("[Post] SELECT completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] SELECT failed.");
		}

		return postList;
	}

	public void update(PostDTO dto) {
		try {
			String sql = "UPDATE post SET pCon=?, pUDate=? WHERE pNo=? AND uID=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getpCon());
			ps.setString(2, dto.getpUDate());
			ps.setInt(3, dto.getpNo());
			ps.setString(4, dto.getuID());
			ps.executeUpdate();

			System.out.println("[Post] UPDATE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] UPDATE failed.");
		}
	}

	public int updateLike(PostDTO dto, int value) {
		int pLike = 0;
		try {
			String sql = "SELECT pLike FROM post WHERE pNo=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getpNo());
			ps.executeQuery();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pLike = rs.getInt("pLike");
			}

			String sql2 = "UPDATE post SET pLike=? WHERE pNo=? ";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, pLike + value);
			ps2.setInt(2, dto.getpNo());
			ps2.executeUpdate();

			System.out.println("[Post] UPDATE pLike completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] UPDATE pLike failed.");
		}
		return (pLike + value);
	}

	public void delete(PostDTO dto) {
		try {
			String sql = "DELETE FROM post WHERE pNo=? AND uID=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getpNo());
			ps.setString(2, dto.getuID());

			ps.executeUpdate();

			System.out.println("[Post] DELETE completed.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---[Post] DELETE failed.");
		}
	}

}
