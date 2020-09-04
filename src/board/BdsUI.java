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
		Font font = new Font("맑은고딕", Font.BOLD, 30);
		Font font2 = new Font("맑은고딕", Font.BOLD, 40);
		
		FlowLayout layout = new FlowLayout();
		
		setTitle("게시판");
		setSize(300,620);
		setLayout(layout);
		getContentPane().setBackground(Color.darkGray);
		
		JLabel l1 = new JLabel("나의 게시판");
		JLabel l2 = new JLabel("I    D");
		JLabel l3 = new JLabel("제   목");
		JLabel l4 = new JLabel("내   용");
		JLabel l5 = new JLabel("글쓴이");
		
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
		
		JButton btn = new JButton("등록");
		btn.setFont(font);
		btn.setBackground(Color.green);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이곳이 control
				// 1 입력한 값 가지고 오기
				String id = t1.getText();
				String title = t2.getText();
				String content = t3.getText();
				String writer = t4.getText();
				
				// 2 DAO에 처리하도록 전달
				// dto를 만든 이유(set/get)
				// 많은 양의 데이터를 가방에 넣어서 전달하려고
				// 가방 역할의 클래스를 Data Transfer Object
				// DTO, 값만 넣었다 뺐다 한다고 해서 Value Object(VO)
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
		
		JButton btn2 = new JButton("삭제");
		btn2.setFont(font);
		btn2.setBackground(Color.red);
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이곳이 control
				// 1 입력한 값 가지고 오기
				String id = t1.getText();

				// 2 DAO에 처리하도록 전달
				BdsDAO dao = new BdsDAO();
				dao.delete(id);
				
				t1.setText(null);
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
			}
		});
		
		JButton btn3 = new JButton("수정");
		btn3.setFont(font);
		btn3.setBackground(Color.yellow);
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이곳이 control
				// 1 입력한 값 가지고 오기
				String id = t1.getText();
				String title = t2.getText();
				String content = t3.getText();
				
				// 2 DAO에 처리하도록 전달
				BdsDAO dao = new BdsDAO();
				dao.update(id, title, content);
				
				t1.setText(null);
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
			}
		});
		
		JButton btn4 = new JButton("조회");
		btn4.setFont(font);
		btn4.setBackground(Color.blue);
		btn4.setForeground(Color.white);
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이곳이 control
				// 1 입력한 값 가지고 오기
				String id = t1.getText();

				// 2 DAO에 처리하도록 전달
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
