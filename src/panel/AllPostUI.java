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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import frame.FrameComment;
import myMenu.AllPostRUD;
import myMenu.CommentDAO;
import myMenu.HeartDAO;
import myMenu.HeartDTO;
import myMenu.PostDAO;
import myMenu.PostDTO;

public class AllPostUI extends JPanel {

	AllPostRUD rud = new AllPostRUD();
	public int height;

	String loginID = "aaaa"; // 나중에 받아오기
	
	public AllPostUI() {
		try {
			PostDAO pDao = new PostDAO();
			ArrayList<PostDTO> postList = pDao.read();
			
			height = postList.size() * 730; // 스크롤 크기 반환

			if (postList.size() == 0) {
				add(new JLabel("No Post!"));
			}
			else {
				for (int i = 0; i < postList.size(); i++) {
					PostDTO dto = postList.get(i); // 왜그랬니...여기에서 인덱스 돌리면서 봐야지ㅜㅜ			
					add(addPanel(dto.getpNo(), dto.getpImg(), dto.getpCon(), dto.getpCDate(), dto.getpHeart(), dto.getuID()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		setSize(505, 730);
		setPreferredSize(new Dimension(505, 730));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	public JPanel addPanel(int pNo, String pImg, String pCon, String uCDate, int pHeart, String uID) {	
		final String IC_LOC = "E:/2020/java/workspace/instagram/icon/"; // 내가 쓸 아이콘 이미지 위치
		JButton btnDoUpdate = new JButton(); // (편집 선택시 나타나는) 수정 진행 버튼
		JButton btnNoUpdate = new JButton(); // (편집 선택시 나타나는) 수정 취소 버튼
		ImageIcon icUnheart = new ImageIcon(IC_LOC + "icUnheart.png");
		ImageIcon icHeart = new ImageIcon(IC_LOC + "icHeart.png");
		HeartDAO hDao = new HeartDAO();

		JPanel pnPost = new JPanel(); // 바탕이 될 패널
		pnPost.setBackground(Color.white);
		pnPost.setLayout(null);
		pnPost.setSize(500, 730);
		pnPost.setPreferredSize(new Dimension(500, 730));

		Font font = new Font("맑은고딕", 0, 12);
		Font font2 = new Font("맑은고딕", 0, 9);

		JLabel lbProfile = new JLabel(new ImageIcon(IC_LOC + "icPerson.png")); // 유저프로필 ★ 유저가 지정할 수 있게 할까?
		lbProfile.setBounds(10, 10, 48, 48);

		JLabel lbCDate = new JLabel(); // 게시글 생성일 ★나중에 수정일 업데이트 되면?
		lbCDate.setText(uCDate);
		lbCDate.setBounds(70, 20, 150, 10);
		lbCDate.setFont(font);

		JLabel lbUID = new JLabel(); // 유저아이디
		lbUID.setText(uID);
		lbUID.setBounds(70, 30, 100, 20);
		lbUID.setFont(font);

		JLabel lbImg = new JLabel(); // 게시글 사진
		lbImg.setIcon(new ImageIcon(pImg));
		lbImg.setBounds(10, 60, 480, 480);

		JTextArea taCon = new JTextArea(); // 게시글 내용
		taCon.setText(pCon);
		taCon.setBounds(10, 610, 480, 70);
		taCon.setFont(font);
		taCon.setFocusable(false);

		btnDoUpdate.setBounds(355, 690, 60, 30); // 수정 진행 버튼
		btnDoUpdate.setFont(font2);
		btnDoUpdate.setText("Done");
		btnDoUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
				String pUDate = String.valueOf(format.format(new Date()));

				rud.updatePost(pNo, taCon.getText(), pUDate);

				taCon.setFocusable(false);
				taCon.setBorder(null);
				pnPost.remove(btnDoUpdate);
				pnPost.remove(btnNoUpdate);
				pnPost.repaint();
			}
		});

		btnNoUpdate.setBounds(420, 690, 65, 30); // 수정 취소 버튼
		btnNoUpdate.setFont(font2);
		btnNoUpdate.setText("cancel");
		btnNoUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				taCon.setFocusable(false);
				taCon.setBorder(null);
				pnPost.remove(btnDoUpdate);
				pnPost.remove(btnNoUpdate);
				pnPost.repaint();

				System.out.println("수정 취소");
			}
		});

		JButton btnUpdate = new JButton(); // 수정 여부 버튼
		btnUpdate.setIcon(new ImageIcon(IC_LOC + "icUpdate.png"));
		btnUpdate.setBounds(405, 10, 40, 40);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnPost, "수정하시겠습니까?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) { // 수정
					pnPost.add(btnDoUpdate);
					pnPost.add(btnNoUpdate);
					taCon.setFocusable(true);
					
					taCon.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					pnPost.repaint(); // 버튼 추가했으니 새고
				} else if (result == JOptionPane.NO_OPTION) { // 수정 취소
					JOptionPane.showMessageDialog(pnPost, "cancle");
				}
			}
		});

		JButton btnDelete = new JButton(); // 삭제 여부 버튼
		btnDelete.setIcon(new ImageIcon(IC_LOC + "icDelete.png"));
		btnDelete.setBounds(450, 10, 40, 40);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnPost, "삭제하시겠습니까?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) { // 삭제
					rud.deletePost(pNo);

					pnPost.revalidate();
					pnPost.repaint(); // 지금 패널 말고 부모가 새고되어야 하는데..이건 어떻게 해야하지?
				} else if (result == JOptionPane.NO_OPTION) { // 삭제 취소
					JOptionPane.showMessageDialog(pnPost, "cancle");
				}
			}
		});

		JLabel lbHeart = new JLabel();
		lbHeart.setText("Heart " + pHeart);
		lbHeart.setBounds(125, 550, 48, 48);

		JButton btnHeart = new JButton();
		if (hDao.read(pNo, loginID)) { // 이미 눌렀어요
			btnHeart.setIcon(icHeart);
		}
		else { // 아직 안 눌렀어요
			btnHeart.setIcon(icUnheart);
		}
		btnHeart.setBounds(10, 550, 48, 48);
		btnHeart.setText(null);
		btnHeart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PostDAO dao = new PostDAO();
				HeartDTO hDto = new HeartDTO();
				int pHeart = 0;
				
				if (btnHeart.getIcon() == icUnheart) {
					btnHeart.setIcon(icHeart);

					pHeart = dao.update(pNo, 1);

					hDto.setpNo(pNo);
					hDto.setuID(loginID);
					
					
					hDao.create(hDto); // ?????

					lbHeart.setText("Heart " + pHeart);
					pnPost.repaint();
				}
				else if (btnHeart.getIcon() == icHeart) {
					btnHeart.setIcon(icUnheart);

					pHeart = dao.update(pNo, -1);

					hDto.setpNo(pNo);
					hDto.setuID(loginID);
					
					
					hDao.delete(hDto);

					lbHeart.setText("Heart " + pHeart);
					pnPost.repaint();
				}
			}
		});

		JButton btnComment = new JButton();
		btnComment.setIcon(new ImageIcon(IC_LOC + "icComment.png"));
		btnComment.setBounds(65, 550, 48, 48);
		btnComment.setFont(font);
		btnComment.setText(null);
		btnComment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FrameComment comment = new FrameComment(pNo);
				comment.setVisible(true);
			}
		});

		JLabel lbComment = new JLabel();
		lbComment.setBounds(410, 540, 100, 70);
		lbComment.setFont(font);		
		lbComment.setText("Comment " + String.valueOf(new CommentDAO().read(pNo).size()));

		pnPost.add(lbProfile);
		pnPost.add(lbUID);
		pnPost.add(btnUpdate);
		pnPost.add(btnDelete);
		pnPost.add(lbImg);
		pnPost.add(lbCDate);
		pnPost.add(taCon);
		pnPost.add(lbHeart);
		pnPost.add(btnHeart);
		pnPost.add(btnComment);
		pnPost.add(lbComment);

		return pnPost;
	}

}
