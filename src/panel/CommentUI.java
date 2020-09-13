package panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import myMenu.CommentRUD;

public class CommentUI extends JPanel {

	String loginID = "aaaa";

	public CommentUI(int pNo) {
		JTextArea taCon = new JTextArea();
		taCon.setBounds(10, 10, 420, 50);

		JButton btnCreate = new JButton("post");
		btnCreate.setBounds(440, 10, 60, 50);
		btnCreate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cCDate = String.valueOf(new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
				String cCon = taCon.getText();

				if (!cCon.matches("")) {
					CommentRUD rud = new CommentRUD();
					rud.createComment(cCon, cCDate, loginID, pNo); // ?????

					taCon.setText(null);
				} else {
					JOptionPane.showMessageDialog(getParent(), "No Content!");
				}
			}
		});

		add(btnCreate);
		add(taCon);

		setLayout(null);
		setSize(540, 70);
		setPreferredSize(new Dimension(540, 70));
	}
}
