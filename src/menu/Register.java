package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Register extends JPanel {

	public Register() {
		ImageIcon img = new ImageIcon("InsLogo.png");
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lb = new JLabel(img);
		lb.setBounds(74, 127, 363, 111);

		JLabel lb2 = new JLabel("���̵�");
		lb2.setFont(new Font("�������", Font.BOLD, 17));
		lb2.setBounds(157, 276, 73, 45);

		JLabel lb3 = new JLabel("��й�ȣ");
		lb3.setFont(new Font("�������", Font.BOLD, 17));
		lb3.setBounds(157, 347, 89, 45);

		JLabel lb4 = new JLabel("��й�ȣ ���Է�");
		lb4.setFont(new Font("�������", Font.BOLD, 17));
		lb4.setBounds(157, 430, 126, 45);

		JLabel lb5 = new JLabel("��ȭ��ȣ");
		lb5.setFont(new Font("�������", Font.BOLD, 17));
		lb5.setBounds(157, 511, 107, 45);

		JTextField tfID = new JTextField(10);
		tfID.setFont(new Font("�������", Font.PLAIN, 20));
		tfID.setBackground(Color.WHITE);
		tfID.setColumns(10);
		tfID.setBounds(158, 316, 222, 31);

		JTextField tfPNum = new JTextField(10);
		tfPNum.setFont(new Font("굴림", Font.PLAIN, 20));
		tfPNum.setBackground(Color.WHITE);
		tfPNum.setColumns(10);
		tfPNum.setBounds(157, 546, 222, 31);

		JPasswordField tfPW = new JPasswordField();
		tfPW.setBounds(158, 384, 222, 31);

		JPasswordField tfPWC = new JPasswordField();
		tfPWC.setBounds(158, 465, 222, 31);

		JButton btnRegister = new JButton("���� �Ϸ�");
		btnRegister.setFont(new Font("�������", Font.BOLD, 20));
		btnRegister.setBounds(181, 659, 168, 38);
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String uID = tfID.getText();
				String uPW = String.valueOf(tfPW.getPassword());
				String uPhone = tfPNum.getText();
				String uCDate = "2020-08-26 11:10:23";

				UserDAO dao = new UserDAO();
				UserDTO dto = new UserDTO();
				dto.setuID(uID);
				dto.setuPW(uPW);
				dto.setuPhone(uPhone);
				dto.setuCDate(uCDate);

				dao.createUser(dto);
				JOptionPane.showMessageDialog(null, uID + "�� ���� �Ϸ�!");

				uID = null;
				uPW = null;
				uPhone = null;
				uCDate = null;
			}
		});

		add(lb);
		add(lb2);
		add(tfID);
		add(lb3);
		add(lb3);
		add(tfPW);
		add(lb4);
		add(tfPWC);
		add(tfPNum);
		add(lb5);
		add(btnRegister);
		setSize(540, 960);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Register();
	}
}
