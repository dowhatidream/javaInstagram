package myMenu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// �����ϴ� �� �� ���� �ٽ� �ϴ� ��..?

public class Instagram extends JFrame {

	public static void main(String[] args) {
		new Instagram();
	}

	public Instagram() {
		JLabel lbLogo = new JLabel(new ImageIcon("E:/2020/java/workspace/instagram/icon/icLogo.png"));
		lbLogo.setBounds(0, 0, 70, 70);

		JPanel pnBg = new JPanel();

		JScrollPane scroll = new JScrollPane(pnBg);
		scroll.setHorizontalScrollBar(null);
		scroll.setBounds(0, 70, 525, 750);
		scroll.setBorder(null);

		Board board = new Board();
		board.setVisible(true);
		board.setPreferredSize(new Dimension(505, board.height));
		pnBg.add(board);

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(10, 840, 160, 70);
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnBg.removeAll();

				Board board = new Board();
				pnBg.add(board);
				pnBg.repaint();
				board.setVisible(true);
				board.setPreferredSize(new Dimension(505, board.height)); // ¥�ε��� �������� �갡 ���� �ϳ�...��
			}
		});

		JButton btnPost = new JButton("Posting");
		btnPost.setBounds(180, 840, 160, 70);
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Post post = new Post();
				pnBg.removeAll();
				pnBg.add(post);
				pnBg.repaint();
				post.setVisible(true);
				post.setPreferredSize(new Dimension(540, 750)); // ¥�ε��� �������� �갡 ���� �ϳ�...��
			}
		});

		JButton btnMe = new JButton("Me");
		btnMe.setBounds(350, 840, 160, 70);

		add(scroll);

		add(lbLogo);
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
