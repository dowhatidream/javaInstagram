package myMenu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame{

	public static void main(String[] args) {
		new Test();
	}
	
	public Test() {
		JPanel pnBg = new JPanel();
		pnBg.setVisible(true);
		pnBg.setBounds(0, 0, 500, 900);
//		pnBg.setBackground(Color.blue);
		
		Board board = new Board();
		board.setVisible(true);
		board.setPreferredSize(new Dimension(500,900));
		
		pnBg.add(board);
		
		add(pnBg);
		
		setLayout(null);
		setSize(540, 960);
		setVisible(true);
	}

}
