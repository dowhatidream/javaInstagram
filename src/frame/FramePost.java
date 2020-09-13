package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import panel.AllPostUI;
import panel.LogoUI;
import panel.MyPostUI;
import panel.PostUI;
import panel.ProfileUI;

// 새고하는 게 곧 생성 다시 하는 것..?

public class FramePost extends JFrame {

	String loginID = "aaaa";
	JPanel pnBg;
	AllPostUI allPost;
	LogoUI pnLogo;
	LogoUI pnLogo2;
	JScrollPane scroll;
	static public boolean result;

	public static void main(String[] args) {
		new FramePost();
	}

	public FramePost() {
		pnLogo = new LogoUI("INSTAGRAM");
		pnLogo2 = new LogoUI(loginID);

		pnBg = new JPanel();
		scroll = new JScrollPane(pnBg);
		scroll.setHorizontalScrollBar(null);
		scroll.setBounds(0, 70, 525, 750);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUnitIncrement(20);

		allPost = new AllPostUI(); // 진입 시 기본화면 세팅
		allPost.setPreferredSize(new Dimension(505, allPost.height));

		if (allPost.height == 0) {
			pnBg.add(new JLabel("hey no post"));
		}

		if (result) {
			screen(1);
			System.out.println(result);
		}

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(10, 840, 160, 70);
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				screen(1);
			}
		});

		JButton btnPost = new JButton("Posting");
		btnPost.setBounds(180, 840, 160, 70);
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				screen(2);
			}
		});

		JButton btnMe = new JButton("Me");
		btnMe.setBounds(350, 840, 160, 70);
		btnMe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				screen(3);
			}
		});

		pnBg.add(allPost);

		add(pnLogo);
		add(scroll);
		add(btnHome);
		add(btnPost);
		add(btnMe);

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setSize(540, 960);
		setVisible(true);
	}

	public void screen(int status) {
		pnBg.removeAll();

		if (status == 1) {
			System.out.println("난 1");
			pnLogo2.setVisible(false);
			pnLogo.setVisible(true);
			add(pnLogo);

			AllPostUI allPost = new AllPostUI();
			allPost.setPreferredSize(new Dimension(505, allPost.height)); // 짜부되지 않으려면 얘가 길어야 하네...ㅋ
			scroll.setBounds(0, 70, 525, 750);
			pnBg.add(allPost);
			pnBg.repaint();

		}
		if (status == 2) {
			System.out.println("난 2");
			pnLogo.setVisible(true);
			pnLogo2.setVisible(false);
			add(pnLogo);

			PostUI post = new PostUI();
			post.setPreferredSize(new Dimension(540, 750)); // 짜부되지 않으려면 얘가 길어야 하네...ㅋ
			scroll.setBounds(0, 70, 525, 750);
			pnBg.add(post);
			pnBg.repaint();

		}
		if (status == 3) {
			System.out.println("난 3");
			scroll.setBounds(0, 210, 525, 620);

			pnLogo.setVisible(false);
			pnLogo2.setVisible(true);
			add(pnLogo2);

			ProfileUI pnProfile = new ProfileUI();
			pnProfile.setBounds(5, 70, 505, 130);
			add(pnProfile);

			MyPostUI myPost = new MyPostUI();
			myPost.setPreferredSize(new Dimension(540, myPost.height)); // 짜부되지 않으려면 얘가 길어야 하네...ㅋ
			pnBg.add(myPost);
			pnBg.repaint();
		}
	}

//	public void come() {
//		System.out.println("받았어. 화면 바꿀게!");
//
//		screen(1);
//
//		pnBg.removeAll();
//		pnBg.add(allPost);
//	}
}
