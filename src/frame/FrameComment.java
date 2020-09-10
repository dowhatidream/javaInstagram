package frame;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import myMenu.CommentDTO;
import panel.AllCommentUI;
import panel.CommentUI;

public class FrameComment extends JFrame{

	public FrameComment(int pNo) {
		JScrollPane scroll = new JScrollPane();
		scroll.setHorizontalScrollBar(null);
		
		JPanel pnBg = new JPanel();
		pnBg.setLayout(new BoxLayout(pnBg, BoxLayout.Y_AXIS));
		
		AllCommentUI pnAllComent = new AllCommentUI(pNo);
		pnAllComent.setPreferredSize(new Dimension(520, pnAllComent.height));
		
		CommentUI pnComment = new CommentUI(pNo);
		
		
		pnBg.add(pnAllComent);
		pnBg.add(pnComment);
		
		scroll.getViewport().add(pnBg);
		add(scroll);

		setSize(540, 560);
		setVisible(true);
	}
}
