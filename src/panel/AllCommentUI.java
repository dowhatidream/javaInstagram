package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

		for (int i = 0; i < commentList.size(); i++) {
			CommentDTO dto = commentList.get(i);
			add(addPanel(dto.getcNo(), dto.getcCon(), dto.getcCDate(), dto.getcUDate(), dto.getuID(),
					dto.getpNo()));
		}

		setSize(540, 100);
		setPreferredSize(new Dimension(505, 100));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	public JPanel addPanel(int cNo, String cCon, String cCDate, String cUDate, String uID, int pNo) {
		final String IC_LOC = "E:/2020/java/workspace/instagram/icon/"; // 이미지 위치
//		JButton btnDoUpdate = new JButton(); 
//		JButton btnNoUpdate = new JButton();

		JPanel pnComment = new JPanel();
		pnComment.setBackground(Color.white);
		pnComment.setLayout(null);
		pnComment.setSize(500, 90);
		pnComment.setPreferredSize(new Dimension(500, 90));

		Font font = new Font("맑은 고딕", Font.PLAIN, 15);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 15);
		Font font3 = new Font("맑은 고딕", Font.PLAIN, 12);

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

//		btnDoUpdate.setBounds(355, 690, 60, 30); // ���� ���� ��ư
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
//		btnNoUpdate.setBounds(420, 690, 65, 30); // ���� ��� ��ư
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
//				System.out.println("���� ���");
//			}
//		});

		JButton btnUpdate = new JButton();
		btnUpdate.setIcon(new ImageIcon(IC_LOC + "icUpdate.png"));
		btnUpdate.setBounds(420, 10, 30, 30);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnComment, "Update?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {

					pnComment.repaint();
				} else if (result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(pnComment, "cancle");
				}
			}
		});

		JButton btnDelete = new JButton();
		btnDelete.setIcon(new ImageIcon(IC_LOC + "icDelete.png"));
		btnDelete.setBounds(460, 10, 30, 30);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnComment, "Delete?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					rud.deletePost(cNo);

					pnComment.revalidate();
					pnComment.repaint();
				} else if (result == JOptionPane.NO_OPTION) {
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
