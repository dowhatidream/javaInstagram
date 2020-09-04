package myMenu;

import java.util.ArrayList;

public class CommentRUD {
	CommentDTO dto = new CommentDTO();
	CommentDAO dao = new CommentDAO();
	
	public void createComment(String cCon, String cCDate, String uID, String pNo) {
		try {
			
			dto.setcCon(cCon);
			dto.setcCDate(cCDate);
			dto.setuID(uID);
			dto.setpNo(Integer.valueOf(pNo));
			
			dao.create(dto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[Comment] execute CREATE failed!!");
		}
	}
	
	public ArrayList<String[]> readComment(String pNo) {
		ArrayList<String> comment = new ArrayList<String>(); // �Խñ� �� ���� �� ��(split X)
		ArrayList<String[]> commentList = new ArrayList<String[]>(); // �Խñ� �ϳ��� �߶� 2���� �迭��

		try {
			comment = dao.read(Integer.valueOf(pNo));

			for (int i = 0; i < comment.size(); i++) {
				String[] items = comment.get(i).split(",");
				commentList.add(items);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[Comment] execute READ failed!!");
		}

		return commentList;
	}
}
