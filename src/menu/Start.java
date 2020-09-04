package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start extends JFrame{
	public static void main(String[] args) {
		new Start();
	}
	
	public Start() {
		JPanel startPage = new JPanel();
		startPage.setSize(500, 400);
		
		JButton btnRegister = new JButton("회원가입");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				
				startPage.setVisible(false);
				System.out.println("이동 완료");
				add(register);
//				setContentPane(register);
			}
		});
	
		startPage.add(btnRegister);
		
		add(startPage);
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫으면 아예 꺼짐
		setLocationRelativeTo(null); // 화면 가운데 띄우도록
		setVisible(true); // 현재 프레임
	}
}
