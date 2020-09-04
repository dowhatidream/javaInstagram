package myMenu;

import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Post extends JFrame {
	final static String IMG_LOC = "E:/2020/java/workspace/instagram/img/"; // 유저가 저장하는 이미지
	boolean fileExist; // 사진이 선택되었는지 여부
	File selectF;
	String selectFName;
	String selectFLoc;
	BufferedImage img;

	public static void main(String[] args) {
		new Post();
	}

	public Post() {
		JPanel pnImg = new JPanel();
		pnImg.setBackground(new Color(255, 250, 205));
		pnImg.setBounds(10, 10, 504, 463);

		JPanel pnCon = new JPanel();
		pnCon.setBounds(0, 483, 524, 428);

		JTextArea taCon = new JTextArea();
		taCon.setFont(new Font("맑은고딕", Font.PLAIN, 15));
		taCon.setBounds(10, 10, 504, 292);

		JLabel lbShowImg = new JLabel();
		lbShowImg.setBackground(new Color(255, 250, 205));
		lbShowImg.setBounds(10, 20, 502, 328);

		JButton btnImg = new JButton("사진 선택");
		btnImg.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btnImg.setBounds(10, 312, 504, 50);

		JFileChooser fc = new JFileChooser();

		btnImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(Post.this); // fc를 열어서 리턴값 저장

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectF = fc.getSelectedFile(); // file타입. file name 저장
					selectFName = selectF.getName(); // file name 저장
					selectFLoc = selectF.getAbsolutePath(); // file location 저장

					lbShowImg.setIcon(new ImageIcon(selectFLoc)); // 불러온 file을 label에 나타내기

					fileExist = true; // 필수값이니까 선택했으면 true
				} else {
					JOptionPane.showMessageDialog(pnImg, "Cancelled.");
				}
			}
		});

		JButton btnAdd = new JButton("작성 완료");
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		btnAdd.setBounds(10, 372, 504, 50);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileExist) {
					Date date = new Date();
					PostDTO dto = new PostDTO();
					PostDAO dao = new PostDAO();

					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
					String pCDate = String.valueOf(format.format(date));
					String uID = "aaaa"; // 나중에 받아오기

					try {
						img = ImageIO.read(selectF); // 선택한 파일을 이미지로 읽어들임
						int width = img.getWidth();
						int height = img.getHeight();

						BufferedImage saveImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
						saveImg.createGraphics().drawImage(img, 0, 0, Post.this);

						String imgName = uID + "_" + pCDate + "_" + selectFName;
						File saveFile = new File(IMG_LOC, imgName);
						ImageIO.write(saveImg, "jpg", saveFile);

						String pCon = taCon.getText();
						String pImg = String.valueOf(saveFile);

						dto.setpCon(pCon);
						dto.setpImg(pImg);
						dto.setpCDate(pCDate);
						dto.setuID(uID);

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

		getContentPane().setLayout(null);
		getContentPane().add(pnCon);
		getContentPane().add(pnImg);

		setSize(540, 960);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
