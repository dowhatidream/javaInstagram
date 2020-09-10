package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import myMenu.PostDAO;
import myMenu.PostDTO;

public class PostUI extends JPanel {
	final static String IMG_LOC = "E:/2020/java/workspace/instagram/img/"; // ?ú†??Í∞? ???û•?ïò?äî ?ù¥ÎØ∏Ï?
	boolean fileExist; // ?Ç¨ÏßÑÏù¥ ?Ñ†?Éù?êò?óà?äîÏß? ?ó¨Î∂?
	File selectF;
	String selectFName;
	String selectFLoc;
	BufferedImage img;
	
	String loginID = "aaaa"; // ?ÇòÏ§ëÏóê Î∞õÏïÑ?ò§Í∏?

	public PostUI() {
		JFileChooser fc = new JFileChooser();
		
		JPanel pnImg = new JPanel();
		pnImg.setBackground(new Color(255, 250, 205));
		pnImg.setBounds(0, 0, 504, 480);

		JLabel lbShowImg = new JLabel();
		lbShowImg.setBackground(new Color(255, 250, 205));
		lbShowImg.setBounds(0, 0, 500, 476);

		JPanel pnCon = new JPanel();
		pnCon.setBounds(0, 490, 504, 250);

		JTextArea taCon = new JTextArea();
		taCon.setFont(new Font("ÎßëÏ?Í≥†Îîï", Font.PLAIN, 15));
		taCon.setBounds(0, 0, 504, 140);

		JButton btnImg = new JButton("?Ç¨Ïß? ?Ñ†?Éù");
		btnImg.setFont(new Font("ÎßëÏ? Í≥†Îîï", Font.PLAIN, 17));
		btnImg.setBounds(0, 150, 504, 45);
		btnImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(PostUI.this); // fcÎ•? ?ó¥?ñ¥?Ñú Î¶¨ÌÑ¥Í∞? ???û•

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectF = fc.getSelectedFile(); // file???ûÖ. file name ???û•
					selectFName = selectF.getName(); // file name ???û•
					selectFLoc = selectF.getAbsolutePath(); // file location ???û•

					lbShowImg.setIcon(new ImageIcon(selectFLoc)); // Î∂àÎü¨?ò® file?ùÑ label?óê ?Çò???Ç¥Í∏?

					fileExist = true; // ?ïÑ?àòÍ∞íÏù¥?ãàÍπ? ?Ñ†?Éù?ñà?úºÎ©? true
				} else {
					JOptionPane.showMessageDialog(pnImg, "Cancelled.");
				}
			}
		});

		JButton btnAdd = new JButton("?ûë?Ñ± ?ôÑÎ£?");
		btnAdd.setFont(new Font("ÎßëÏ? Í≥†Îîï", Font.PLAIN, 17));
		btnAdd.setBounds(0, 202, 504, 45);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileExist) {
					try {
						Date date = new Date();
						PostDTO dto = new PostDTO();
						PostDAO dao = new PostDAO();
						
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
						String pCDate = String.valueOf(format.format(date));
						
						img = ImageIO.read(selectF); // ?Ñ†?Éù?ïú ?åå?ùº?ùÑ ?ù¥ÎØ∏Ï?Î°? ?ùΩ?ñ¥?ì§?ûÑ
						int width = img.getWidth();
						int height = img.getHeight();

						BufferedImage saveImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
						saveImg.createGraphics().drawImage(img, 0, 0, PostUI.this);

						String imgName = loginID + "_" + pCDate + "_" + selectFName;
						File saveFile = new File(IMG_LOC, imgName);
						ImageIO.write(saveImg, "jpg", saveFile);

						String pCon = taCon.getText();
						String pImg = String.valueOf(saveFile);

						dto.setpCon(pCon);
						dto.setpImg(pImg);
						dto.setpCDate(pCDate);
						dto.setuID(loginID);

						dao.create(dto);
					} catch (Exception e2) {
						e2.printStackTrace();
					}

					lbShowImg.setIcon(null);
					taCon.setText(null);
					selectF = null;
					selectFName = null;
					selectFLoc = null;
					fileExist = false;

					JOptionPane.showMessageDialog(pnCon, "Completed.");
				} else {
					JOptionPane.showMessageDialog(pnCon, "Select a image!");
				}
			}
		});

		pnImg.add(lbShowImg);
		pnCon.setLayout(null);
		pnCon.add(taCon);
		pnCon.add(btnImg);
		pnCon.add(btnAdd);

		setLayout(null);
		add(pnImg);
		add(pnCon);

		setSize(506, 740);
		setPreferredSize(new Dimension(506, 740));
		setVisible(true);
	}
}
