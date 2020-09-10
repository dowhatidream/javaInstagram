package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import myMenu.PostDAO;

public class ProfileUI extends JPanel {

	String loginID = "aaaa";

	public ProfileUI() {
		final String IC_LOC = "E:/2020/java/workspace/instagram/icon/"; // 내가 쓸 아이콘 이미지 위치

		JLabel lbProfile = new JLabel(new ImageIcon(IC_LOC + "icPerson.png")); // 유저프로필 ★ 유저가 지정할 수 있게 할까?
		lbProfile.setBounds(0, 0, 64, 64);
		
		JLabel lbIntro = new JLabel("Life is short...you need JAVA");
		lbIntro.setBounds(10, 50, 200, 64);
		
		JLabel lbCount = new JLabel(new PostDAO().readMine(loginID)+"");
		lbCount.setBounds(450, 15, 30, 30);
		lbCount.setFont(new Font("맑은고딕", 0, 24));
		lbCount.setBackground(Color.yellow);

		JLabel lbText = new JLabel("POST");
		lbText.setBounds(437, 37, 60, 30);
		lbText.setFont(new Font("맑은고딕", 0, 14));
		lbText.setBackground(Color.yellow);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(10, 100, 482, 25);

		add(lbIntro);
		add(btnEdit);
		add(lbCount);
		add(lbText);
		add(lbProfile);
		setLayout(null);
		setSize(505, 110);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(505, 110));
		setVisible(true);
	}
}
