package myMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Comment extends JFrame {
	CommentRUD rud = new CommentRUD();

	public Comment(String pNo) {
		JScrollPane scroll = new JScrollPane();
		scroll.setHorizontalScrollBar(null);

		JPanel pnBg = new JPanel();
		pnBg.setLayout(new BoxLayout(pnBg, BoxLayout.Y_AXIS));

		ArrayList<String[]> commentList = rud.readComment(pNo);

		if (!commentList.isEmpty()) {
			for (int i = 0; i < commentList.size(); i++) { // cNo, cCon, cCDate, cUDate, uID, pNo (String)
				pnBg.add(addPanel(commentList.get(i)[0], commentList.get(i)[1], commentList.get(i)[2],
						commentList.get(i)[3], commentList.get(i)[4], commentList.get(i)[5])); // getName()하면 cNo 리턴
			}
		} else {
			pnBg.add(new JLabel("No Comment!"));
		}

		JPanel pnCom = new JPanel();
		pnCom.setLayout(null);
		pnCom.setSize(540, 70);
		pnCom.setPreferredSize(new Dimension(540, 70));

		JTextArea txCon = new JTextArea();
		txCon.setBounds(10, 10, 420, 50);

		JButton btnCreate = new JButton("완료");
		btnCreate.setBounds(440, 10, 60, 50);
		btnCreate.setFont(new Font("맑은고딕", Font.PLAIN, 12));
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
				String cCDate = String.valueOf(format.format(new Date()));
				String uID = "dddd"; // 나중에 받아오기
				String cCon = txCon.getText();

				if (!cCon.matches("")) {
					CommentRUD rud = new CommentRUD();
					rud.createComment(cCon, cCDate, uID, pNo);
					pnBg.revalidate();
					pnBg.repaint();
					pnCom.repaint();
					
					txCon.setText(null);
				}
				else {
					JOptionPane.showMessageDialog(pnCom, "No Content!");
				}
				
			}
		});

		pnCom.add(btnCreate);
		pnCom.add(txCon);

		pnBg.add(pnCom); // 댓글모음패널 위에 댓글창 올리기
		scroll.getViewport().add(pnBg); // scroll 위에 댓글모음패널 올리기
		add(scroll); // 프레임 위에 scroll 올리기

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setSize(540, 560);
		setVisible(true);
	}

	public JPanel addPanel(String cNo, String cCon, String cCDate, String cUDate, String uID, String pNo) {
		JPanel pnComment = new JPanel();
		pnComment.setBackground(Color.white);
		pnComment.setLayout(null);
		pnComment.setSize(500, 90);
		pnComment.setPreferredSize(new Dimension(500, 90));
		pnComment.setName(cNo);

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

		pnComment.add(lbProfile);
		pnComment.add(lbUID);
		pnComment.add(lbCon);
		pnComment.add(lbCDate);

		return pnComment;
	}
}
