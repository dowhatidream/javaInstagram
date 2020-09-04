package myMenu;

import java.util.ArrayList;

public class BoardRUD {
	PostDAO dao = new PostDAO();
	PostDTO dto = new PostDTO();

	public ArrayList<String[]> readPost() {
		ArrayList<String> post = new ArrayList<String>(); // �Խñ� �� ���� �� ��(split X)
		ArrayList<String[]> postList = new ArrayList<String[]>(); // �Խñ� �ϳ��� �߶� 2���� �迭��

		try {
			post = dao.read();

			for (int i = 0; i < post.size(); i++) {
				String[] items = post.get(i).split(",");
				postList.add(items);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[Post] execute READ failed!!");
		}

		return postList;
	}

	public void updatePost(String pNo, String pCon, String pUDate, String uID) {
		try {
			dto.setpCon(pCon);
			dto.setpUDate(pUDate);
			dto.setpNo(Integer.valueOf(pNo));
			dto.setuID(uID);

			dao.update(dto);
		} catch (Exception e) {
			System.out.println("[Post] execute UPDATE failed!!");
		}
	}
	
	public int updatePost(String pNo) {
		int pLike = 0;
		
		try {
			dto.setpNo(Integer.valueOf(pNo));

			pLike = dao.updateLike(dto);
			
		} catch (Exception e) {
			System.out.println("[Post] execute UPDATE failed!!");
		}
		return pLike;
	}

	public void deletePost(String pNo, String uID) {
		try {
			dto.setpNo(Integer.valueOf(pNo));
			dto.setuID(uID);

			dao.delete(dto);
		} catch (Exception e) {
			System.out.println("[Post] execute DELETE failed!!");
		}
	}
}
