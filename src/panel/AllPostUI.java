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

	String loginID = "aaaa"; // ���߿� �޾ƿ���
	
	public AllPostUI() {
		try {
			PostDAO pDao = new PostDAO();
			ArrayList<PostDTO> postList = pDao.read();
			
			height = postList.size() * 730; // ��ũ�� ũ�� ��ȯ

			if (postList.size() == 0) {
				add(new JLabel("No Post!"));
			}
			else {
				for (int i = 0; i < postList.size(); i++) {
					PostDTO dto = postList.get(i); // �ֱ׷���...���⿡�� �ε��� �����鼭 �������̤�			
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
		final String IC_LOC = "E:/2020/java/workspace/instagram/icon/"; // ���� �� ������ �̹��� ��ġ
		JButton btnDoUpdate = new JButton(); // (���� ���ý� ��Ÿ����) ���� ���� ��ư
		JButton btnNoUpdate = new JButton(); // (���� ���ý� ��Ÿ����) ���� ��� ��ư
		ImageIcon icUnheart = new ImageIcon(IC_LOC + "icUnheart.png");
		ImageIcon icHeart = new ImageIcon(IC_LOC + "icHeart.png");
		HeartDAO hDao = new HeartDAO();

		JPanel pnPost = new JPanel(); // ������ �� �г�
		pnPost.setBackground(Color.white);
		pnPost.setLayout(null);
		pnPost.setSize(500, 730);
		pnPost.setPreferredSize(new Dimension(500, 730));

		Font font = new Font("�������", 0, 12);
		Font font2 = new Font("�������", 0, 9);

		JLabel lbProfile = new JLabel(new ImageIcon(IC_LOC + "icPerson.png")); // ���������� �� ������ ������ �� �ְ� �ұ�?
		lbProfile.setBounds(10, 10, 48, 48);

		JLabel lbCDate = new JLabel(); // �Խñ� ������ �ڳ��߿� ������ ������Ʈ �Ǹ�?
		lbCDate.setText(uCDate);
		lbCDate.setBounds(70, 20, 150, 10);
		lbCDate.setFont(font);

		JLabel lbUID = new JLabel(); // �������̵�
		lbUID.setText(uID);
		lbUID.setBounds(70, 30, 100, 20);
		lbUID.setFont(font);

		JLabel lbImg = new JLabel(); // �Խñ� ����
		lbImg.setIcon(new ImageIcon(pImg));
		lbImg.setBounds(10, 60, 480, 480);

		JTextArea taCon = new JTextArea(); // �Խñ� ����
		taCon.setText(pCon);
		taCon.setBounds(10, 610, 480, 70);
		taCon.setFont(font);
		taCon.setFocusable(false);

		btnDoUpdate.setBounds(355, 690, 60, 30); // ���� ���� ��ư
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

		btnNoUpdate.setBounds(420, 690, 65, 30); // ���� ��� ��ư
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

				System.out.println("���� ���");
			}
		});

		JButton btnUpdate = new JButton(); // ���� ���� ��ư
		btnUpdate.setIcon(new ImageIcon(IC_LOC + "icUpdate.png"));
		btnUpdate.setBounds(405, 10, 40, 40);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnPost, "�����Ͻðڽ��ϱ�?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) { // ����
					pnPost.add(btnDoUpdate);
					pnPost.add(btnNoUpdate);
					taCon.setFocusable(true);
					
					taCon.setBorder(BorderFactory.createLineBorder(Color.black, 1));
					pnPost.repaint(); // ��ư �߰������� ����
				} else if (result == JOptionPane.NO_OPTION) { // ���� ���
					JOptionPane.showMessageDialog(pnPost, "cancle");
				}
			}
		});

		JButton btnDelete = new JButton(); // ���� ���� ��ư
		btnDelete.setIcon(new ImageIcon(IC_LOC + "icDelete.png"));
		btnDelete.setBounds(450, 10, 40, 40);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(pnPost, "�����Ͻðڽ��ϱ�?", null, JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) { // ����
					rud.deletePost(pNo);

					pnPost.revalidate();
					pnPost.repaint(); // ���� �г� ���� �θ� ����Ǿ�� �ϴµ�..�̰� ��� �ؾ�����?
				} else if (result == JOptionPane.NO_OPTION) { // ���� ���
					JOptionPane.showMessageDialog(pnPost, "cancle");
				}
			}
		});

		JLabel lbHeart = new JLabel();
		lbHeart.setText("Heart " + pHeart);
		lbHeart.setBounds(125, 550, 48, 48);

		JButton btnHeart = new JButton();
		if (hDao.read(pNo, loginID)) { // �̹� �������
			btnHeart.setIcon(icHeart);
		}
		else { // ���� �� �������
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
