package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import panel.AllPostUI;
import panel.LogoUI;
import panel.MyPostUI;
import panel.PostUI;
import panel.ProfileUI;

// �����ϴ� �� �� ���� �ٽ� �ϴ� ��..?

public class FramePost extends JFrame {

	String loginID = "aaaa";

	public static void main(String[] args) {
		new FramePost();
	}

	public FramePost() {
		LogoUI pnLogo = new LogoUI("INSTAGRAM");
		LogoUI pnLogo2 = new LogoUI(loginID);

		JPanel pnBg = new JPanel();
		JScrollPane scroll = new JScrollPane(pnBg);
		scroll.setHorizontalScrollBar(null);
		scroll.setBounds(0, 70, 525, 750);
		scroll.setBorder(null);

		AllPostUI allPost = new AllPostUI(); // ���� �� �⺻ȭ�� ����
		allPost.setPreferredSize(new Dimension(505, allPost.height));

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(10, 840, 160, 70);
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnBg.removeAll();

				pnLogo2.setVisible(false);
				pnLogo.setVisible(true);
				add(pnLogo);

				AllPostUI allPost = new AllPostUI();
				scroll.setBounds(0, 70, 525, 750);
				pnBg.add(allPost);
				pnBg.repaint();
				allPost.setVisible(true);
				allPost.setPreferredSize(new Dimension(505, allPost.height)); // ¥�ε��� �������� �갡 ���� �ϳ�...��
			}
		});

		JButton btnPost = new JButton("Posting");
		btnPost.setBounds(180, 840, 160, 70);
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnBg.removeAll();

				pnLogo.setVisible(true);
				pnLogo2.setVisible(false);
				add(pnLogo);
				
				PostUI post = new PostUI();
				scroll.setBounds(0, 70, 525, 750);
				pnBg.add(post);
				pnBg.repaint();
				post.setVisible(true);
				post.setPreferredSize(new Dimension(540, 750)); // ¥�ε��� �������� �갡 ���� �ϳ�...��
			}
		});

		JButton btnMe = new JButton("Me");
		btnMe.setBounds(350, 840, 160, 70);
		btnMe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnBg.removeAll();
				scroll.setBounds(0, 210, 525, 620);

				pnLogo.setVisible(false);
				pnLogo2.setVisible(true);
				add(pnLogo2);

				ProfileUI pnProfile = new ProfileUI();
				pnProfile.setBounds(5, 70, 505, 130);
				add(pnProfile);

				MyPostUI myPost = new MyPostUI();
				pnBg.add(myPost);
				pnBg.repaint();

				myPost.setVisible(true);
				myPost.setPreferredSize(new Dimension(540, myPost.height)); // ¥�ε��� �������� �갡 ���� �ϳ�...��
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
}
