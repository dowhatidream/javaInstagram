package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;


public class DBConnectMain {
	public static void main(String[] args) throws Exception {
		
		String id = JOptionPane.showInputDialog("id");
		String title = JOptionPane.showInputDialog("title");
		String content = JOptionPane.showInputDialog("content");
		String writer = JOptionPane.showInputDialog("writer");
		
		BdsDAO dao = new BdsDAO();
		BdsDTO dto = new BdsDTO();
		
//		dao.create(); // �̰� �ʹ� ������..�Ķ���� ���°� �������־
		
	}
}
