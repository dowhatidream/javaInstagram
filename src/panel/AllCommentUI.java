package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import myMenu.CommentDAO;
import myMenu.CommentDTO;
import myMenu.CommentRUD;

public class AllCommentUI extends JPanel {
	
	CommentRUD rud = new CommentRUD();
	public int height;

	String loginID = "aaaa";

	public AllCommentUI(int pNo) {
		CommentDAO dao = new CommentDAO();
		ArrayList<CommentDTO> commentList = dao.read(pNo);
		
		height = commentList.size() * 100;

		if (commentList.size() == 0) {
			add(new JLabel("No Comment!"));
		} else {
			for (int i = 0; i < commentList.size(); i++) {
				CommentDTO dto = commentList.get(i);
				add(addPanel(dto.getcNo(), dto.getcCon(), dto.getcCDate(), dto.getcUDate(), dto.getuID(), dto.getpNo()));
			}
		}

		setSize(540, 100);
		setPreferredSize(new Dimension(505, 100));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	public JPanel addPanel(int cNo, String cCon, String cCDate, String cUDate, String uID, int pNo) {
		final String IC_LOC = "E:/2020/java/workspace/instagram/icon/"; // 내가 쓸 아이콘 이미지 위치
//		JButton btnDoUpdate = new JButton(); // (편집 선택시 나타나는) 수정 진행 버튼
//		JButton btnNoUpdate = new JButton(); // (편집 선택시 나타나는) 수정 취소 버튼

		JPanel pnComment = new JPanel();
		pnComment.setBackground(Color.white);
		pnComment.setLayout(null);
		pnComment.setSize(500, 90);
		pnComment.setPreferredSize(new Dimension(500, 90));

		Font font = new Font("맑은고딕", Font.PLAIN, 15);
		Font font2 = new Font("맑은고딕", Font.BOLD, 15);
		Font font3 = new Font("맑은고딕", Font.PLAIN, 12);

		JLabel lbProfile = new JLabel();
		lbProfile.setBounds(10, 10, 55, 55);
		lbProfile.setIcon(new ImageIcon("E:/2020/java/workspace/instagram/icon/icPerson.png"));

		JLabel lbUID = new JLabel();
		lbUID.setBounds(75, 15, 100, 20);
		lbUID.setFont(font2);
		lbUID.setText(uID);

		JLabel lbCon = new JLabel();
		lbCon.setBounds(75, 27, 400, 40);
		lbCon.setFont(font);
		lbCon.setText(cCon);

		JLabel lbCDate = new JLabel();
		lbCDate.setBounds(75, 60, 150, 20);
		lbCDate.setFont(font3);
		lbCDate.setText(cCDate);

//		btnDoUpdate.setBounds(355, 690, 60, 30); // 수정 진행 버튼
//		btnDoUpdate.setFont(font2);
//		btnDoUpdate.setText("Done");
//		btnDoUpdate.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
//				String cUDate = String.valueOf(format.format(new Date()));
//
////				rud.updatePost(cNo, taCon.getText(), cUDate);
////
////				taCon.setFocusable(false);
////				taCon.setBorder(null);
//				pnComment.remove(btnDoUpdate);
//				pnComment.remove(btnNoUpdate);
//				pnComment.repaint();
//			}
//		});
//
//		btnNoUpdate.setBounds(420, 690, 65, 30); // 수정 취소 버튼
//		btnNoUpdate.setFont(font2);
//		btnNoUpdate.setText("cancel");
//		btnNoUpdate.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
////				taCon.setFocusable(false);
//
//				pnComment.remove(btnDoUpdate);
//				pnComment.remove(btnNoUpdate);
//				pnComment.repaint();
//
//				System.out.println("수정 취소");
//			}
//		});

		JButton btnUpdate = new JButton(); // 수정 여부 버튼
		btnUpdate.setIcon(new ImageIcon(IC_LOC + "icUpdate.png"));
		btnUpdate.setBounds(420, 10, 30, 30);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnComment, "수정하시겠습니까?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) { // 수정
//					pnComment.add(btnDoUpdate);
//					pnComment.add(btnNoUpdate);
//					taCon.setFocusable(true);


					pnComment.repaint(); // 버튼 추가했으니 새고
				} else if (result == JOptionPane.NO_OPTION) { // 수정 취소
					JOptionPane.showMessageDialog(pnComment, "cancle");
				}
			}
		});

		JButton btnDelete = new JButton(); // 삭제 여부 버튼
		btnDelete.setIcon(new ImageIcon(IC_LOC + "icDelete.png"));
		btnDelete.setBounds(460, 10, 30, 30);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnComment, "삭제하시겠습니까?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) { // 삭제
					rud.deletePost(cNo);

					pnComment.revalidate();
					pnComment.repaint(); // 지금 패널 말고 부모가 새고되어야 하는데..이건 어떻게 해야하지?
				} else if (result == JOptionPane.NO_OPTION) { // 삭제 취소
					JOptionPane.showMessageDialog(pnComment, "cancle");
				}
			}
		});

//		pnComment.add(btnDoUpdate);
//		pnComment.add(btnNoUpdate);
		pnComment.add(btnUpdate);
		pnComment.add(btnDelete);
		pnComment.add(lbProfile);
		pnComment.add(lbUID);
		pnComment.add(lbCon);
		pnComment.add(lbCDate);

		return pnComment;
	}
}
