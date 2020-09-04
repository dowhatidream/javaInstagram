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

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(10, 840, 160, 70);
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Board board = new Board();
				board.setVisible(true);				
				board.setPreferredSize(new Dimension(505, board.height)); // 짜부되지 않으려면 얘가 길어야 하네...ㅋ
				pnBg.add(board);
			}
		});

		JButton btnPost = new JButton("Posting");
		btnPost.setBounds(180, 840, 160, 70);

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
