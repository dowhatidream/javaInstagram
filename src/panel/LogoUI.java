package panel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoUI extends JPanel {

	public LogoUI(String title) {

		JLabel lbLogo = new JLabel(new ImageIcon("E:/2020/java/workspace/instagram/icon/icLogo.png"));
		lbLogo.setBounds(0, 0, 70, 70);
		
		JLabel lbText = new JLabel(title);
		lbText.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 20));
		lbText.setBounds(80, -5, 200, 80);
		
		add(lbText);
		add(lbLogo);
		
		setLayout(null);
		setSize(505, 70);
		setPreferredSize(new Dimension(505, 70));
		setVisible(true);
	}
}
