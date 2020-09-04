package board;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class BdsUI extends JFrame{

	public BdsUI() {
		Font font = new Font("�������", Font.BOLD, 30);
		Font font2 = new Font("�������", Font.BOLD, 40);
		
		FlowLayout layout = new FlowLayout();
		
		setTitle("�Խ���");
		setSize(300,620);
		setLayout(layout);
		getContentPane().setBackground(Color.darkGray);
		
		JLabel l1 = new JLabel("���� �Խ���");
		JLabel l2 = new JLabel("I    D");
		JLabel l3 = new JLabel("��   ��");
		JLabel l4 = new JLabel("��   ��");
		JLabel l5 = new JLabel("�۾���");
		
		l1.setFont(font2);
		l2.setFont(font);
		l3.setFont(font);
		l4.setFont(font);
		l5.setFont(font);
		
		l1.setForeground(Color.white);
		l2.setForeground(Color.white);
		l3.setForeground(Color.white);
		l4.setForeground(Color.white);
		l5.setForeground(Color.white);
		
		JTextField t1 = new JTextField(10);
		JTextField t2 = new JTextField(10);
		JTextField t3 = new JTextField(10);
		JTextField t4 = new JTextField(10);
		
		t1.setFont(font);
		t2.setFont(font);
		t3.setFont(font);
		t4.setFont(font);
		
		JButton btn = new JButton("���");
		btn.setFont(font);
		btn.setBackground(Color.green);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �̰��� control
				// 1 �Է��� �� ������ ����
				String id = t1.getText();
				String title = t2.getText();
				String content = t3.getText();
				String writer = t4.getText();
				
				// 2 DAO�� ó���ϵ��� ����
				// dto�� ���� ����(set/get)
				// ���� ���� �����͸� ���濡 �־ �����Ϸ���
				// ���� ������ Ŭ������ Data Transfer Object
				// DTO, ���� �־��� ���� �Ѵٰ� �ؼ� Value Object(VO)
				BdsDAO dao = new BdsDAO();
				BdsDTO dto = new BdsDTO();
				dto.setId(id);
				dto.setTitle(title);
				dto.setContent(content);
				dto.setWriter(writer);
				
				dao.create(dto);
				
				t1.setText(null);
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
			}
		});
		
		JButton btn2 = new JButton("����");
		btn2.setFont(font);
		btn2.setBackground(Color.red);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �̰��� control
				// 1 �Է��� �� ������ ����
				String id = t1.getText();

				// 2 DAO�� ó���ϵ��� ����
				BdsDAO dao = new BdsDAO();
				dao.delete(id);
				
				t1.setText(null);
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
			}
		});
		
		JButton btn3 = new JButton("����");
		btn3.setFont(font);
		btn3.setBackground(Color.yellow);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �̰��� control
				// 1 �Է��� �� ������ ����
				String id = t1.getText();
				String title = t2.getText();
				String content = t3.getText();
				
				// 2 DAO�� ó���ϵ��� ����
				BdsDAO dao = new BdsDAO();
				dao.update(id, title, content);
				
				t1.setText(null);
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
			}
		});
		
		JButton btn4 = new JButton("��ȸ");
		btn4.setFont(font);
		btn4.setBackground(Color.blue);
		btn4.setForeground(Color.white);
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �̰��� control
				// 1 �Է��� �� ������ ����
				String id = t1.getText();

				// 2 DAO�� ó���ϵ��� ����
				BdsDAO dao = new BdsDAO();
				BdsDTO bag = dao.read(id);
				
				t1.setText(bag.getId());
				t2.setText(bag.getTitle());
				t3.setText(bag.getContent());
				t4.setText(bag.getWriter());
			}
		});

		add(l1);
		
		add(l2);
		add(t1);
		add(l3);
		add(t2);
		add(l4);
		add(t3);
		add(l5);
		add(t4);
		
		add(btn);
		add(btn2);
		add(btn3);
		add(btn4);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BdsUI();
	}
}
