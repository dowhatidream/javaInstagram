package menu;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Register2 extends JPanel{
//	public static void main(String[] args) {
//		new Register();
//	}
	
	public Register2() {
		System.out.println("회원가입 화면으로 왔당");
		FlowLayout flow = new FlowLayout();
		
		JPanel registerPage = new JPanel(flow);
		setSize(500, 400);
		
		JLabel l = new JLabel("회원가입 화면");
		
		JButton btn = new JButton("메인가기");

		add(btn);
		add(l);
		add(registerPage);
		setVisible(true);		
	}

}
