package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

import frame.FramePost;
import myMenu.PostDAO;
import myMenu.PostDTO;

public class PostUI extends JPanel {
	boolean fileExist; // 파일 선택여부
	File selectF;
	String selectFName;
	String selectFLoc;
	Image resizeImg;

	String loginID = "aaaa"; // (임시)나중에 현재 로그인 유저 받아오기
	
	public PostUI() {
		final String IMG_LOC = "E:/2020/java/workspace/instagram/img/"; // 내가 사용할 이미지 위치
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
		taCon.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		taCon.setBounds(0, 0, 504, 140);

		JButton btnImg = new JButton("Select a image");
		btnImg.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnImg.setBounds(0, 150, 504, 45);
		btnImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(PostUI.this); // fc 선택 결과
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectF = fc.getSelectedFile(); // 현재 선택한 file
					selectFName = selectF.getName(); // file name
					selectFLoc = selectF.getAbsolutePath(); // file location
					
					try {
						BufferedImage img = ImageIO.read(selectF); // 선택한 파일을 이미지로 읽어들이기					
						resizeImg = img.getScaledInstance(480, 480, Image.SCALE_SMOOTH);
						
						lbShowImg.setIcon(new ImageIcon(resizeImg)); // 선택한 파일 저장 위치에서 불러오기
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					fileExist = true; // 파일이 선택되었으면(인스타는 무조건 사진 선택해야 글 올릴 수 있음
				} else {
					JOptionPane.showMessageDialog(pnImg, "Cancelled.");
				}
			}
		});

		JButton btnAdd = new JButton("Post");
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		btnAdd.setBounds(0, 202, 504, 45);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileExist) {
					try {
						PostDTO dto = new PostDTO();
						PostDAO dao = new PostDAO();
						String pCDate = String.valueOf(new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()));
						
						BufferedImage saveImg = new BufferedImage(480, 480, BufferedImage.TYPE_INT_BGR);
						saveImg.createGraphics().drawImage(resizeImg, 0, 0, PostUI.this);

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

//					System.out.println("저장성공했어. 이제 보낼게");
//					new FramePost().come();
//					FramePost.result = true;

//					JOptionPane.showMessageDialog(pnCon, "Completed.");
				} else {
					JOptionPane.showMessageDialog(pnCon, "No image selected!");
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
