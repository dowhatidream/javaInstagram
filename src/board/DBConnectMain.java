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
		
//		dao.create(); // 이건 너무 무식해..파라메터 형태가 정해져있어서
		
	}
}
