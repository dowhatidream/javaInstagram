package myMenu;

public class AllPostRUD {
	PostDAO dao = new PostDAO();
	PostDTO dto = new PostDTO();

	public void updatePost(int pNo, String pCon, String pUDate) {
		try {
			dto.setpCon(pCon);
			dto.setpUDate(pUDate);
			dto.setpNo(pNo);

			dao.update(dto);
		} catch (Exception e) {
			System.out.println("[Post] execute UPDATE failed!!");
		}
	}

	public void deletePost(int pNo) {
		try {
			dto.setpNo(pNo);

			dao.delete(dto);
		} catch (Exception e) {
			System.out.println("[Post] execute DELETE failed!!");
		}
	}
}
