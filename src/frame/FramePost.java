package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import myMenu.NowUser;
import panel.AllPostUI;
import panel.LogoUI;
import panel.MyPostUI;
import panel.PostUI;
import panel.ProfileUI;

// 새고하는 게 곧 생성 다시 하는 것..?

public class FramePost extends JFrame {

	String loginID = NowUser.getloginID(); // 현재 로긴한 유저

	JPanel pnBg;
	LogoUI pnLogo;
	LogoUI pnLogo2;
	JScrollPane scroll;
	ProfileUI pnProfile;
	public static boolean result;
	static public FramePost f;

	public static void main(String[] args) {
		f = new FramePost();
	}

	public FramePost() {
		final String IC_LOC = "E:/2020/java/workspace/instagram/icon/"; // 이미지 위치
		pnLogo = new LogoUI("INSTAGRAM"); // 기본로고(인스타그램 텍스트)
		pnLogo2 = new LogoUI(loginID); // 마이페이지 로고(유저 아이디 텍스트)
		pnProfile = new ProfileUI(); // 프로필 패널

		pnBg = new JPanel(); // 배경 패널
		scroll = new JScrollPane(pnBg); // 스크롤 패널에 배경 패널 얹기
		scroll.setHorizontalScrollBar(null); // 가로 스크롤 막기
		scroll.setBounds(0, 70, 525, 775); // 스크롤 위치 및 크기
		scroll.setBorder(null); // 테두리 안 어울려서 없애줬음
		scroll.getVerticalScrollBar().setUnitIncrement(20); // 스크롤 속도
		scroll.getVerticalScrollBar().setValue(0); // 스크롤 맨 위로

		screenHome(); // 초기 진입 화면

		JButton btnHome = new JButton(); // 메뉴1
		btnHome.setBounds(10, 855, 160, 60);
		btnHome.setContentAreaFilled(false);
//		btnHome.setBorder(null);
		btnHome.setIcon(new ImageIcon(IC_LOC + "icHome2.png"));
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				screenHome();
			}
		});

		JButton btnPost = new JButton(); // 메뉴2
		btnPost.setBounds(180, 855, 160, 60);
		btnPost.setContentAreaFilled(false);
		btnPost.setBorder(null);
		btnPost.setIcon(new ImageIcon(IC_LOC + "icPosting.png"));
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				screenPosting(f);
			}
		});

		JButton btnMe = new JButton(); // 메뉴3
		btnMe.setBounds(350, 855, 160, 60);
		btnMe.setContentAreaFilled(false);
		btnMe.setBorder(null);
		btnMe.setIcon(new ImageIcon(IC_LOC + "icPerson4.png"));
		btnMe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				screenMe();
			}
		});

		add(pnLogo);
		add(scroll);
		add(btnHome);
		add(btnPost);
		add(btnMe);

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(540, 960);
		setVisible(true);
	}

	// 메인화면 버튼 3개(전체 피드, 포스팅, 내 피드)
	public void screenHome() { // 첫번째 화면(전체 피드)
		pnBg.removeAll(); // 기존 패널 컴포넌트 삭제
		scroll.setBounds(0, 70, 525, 775); // 스크롤 위치 다시 잡아주고
		scroll.getVerticalScrollBar().setValue(0); // 맨위로 올리고(근데 안 먹히는 것 같아..)

		pnLogo2.setVisible(false);
		pnLogo.setVisible(true);
		pnProfile.setVisible(false);
		add(pnLogo); // 기본로고만 보이게

		AllPostUI allPost = new AllPostUI(); // 전체 피드 패널 객체 생성
		if (allPost.height == 0) { // 글이 하나도 없으면, 화면에 안 뜬게 아니라 보여줄게 없다고 알림
			pnBg.add(new JLabel("hey you have no post"));
		}
		allPost.setPreferredSize(new Dimension(505, allPost.height)); // 면적 잡아주고
		pnBg.add(allPost);
		pnBg.repaint();
	}

	public void screenPosting(FramePost f) { // 두번째 화면(포스팅)
		pnBg.removeAll(); // 기존 패널 컴포넌트 삭제
		scroll.setBounds(0, 70, 525, 775); // 스크롤 위치 다시 잡아주고

		pnLogo2.setVisible(false);
		pnLogo.setVisible(true);
		pnProfile.setVisible(false);
		add(pnLogo); // 기본로고만 보이게

		PostUI post = new PostUI(f); // 포스팅 패널 객체 생성. 메인화면 메소드 호출하려고 FramePost f 넘겨줌
		post.setPreferredSize(new Dimension(540, 750)); // 면적 잡아주고
		pnBg.add(post);
		pnBg.repaint();
	}

	public void screenMe() { // 세번째 화면(내 피드)
		pnBg.removeAll(); // 기존 패널 컴포넌트 삭제
		scroll.setBounds(0, 210, 525, 640); // 로고 위치 때문에 스크롤 크기 줄여주기
		scroll.getVerticalScrollBar().setValue(0);

		pnLogo.setVisible(false);
		pnLogo2.setVisible(true); // 내아이디로고 보이게
		add(pnLogo2);

		pnProfile.setBounds(5, 68, 505, 130);
		pnProfile.setVisible(true);
		add(pnProfile);

		MyPostUI myPost = new MyPostUI();
		if (myPost.height == 0) { // 댓글이 하나도 없으면, 화면에 안 뜬게 아니라 보여줄게 없다고 알림
			pnBg.add(new JLabel("hey you have no post2"));
		}
		myPost.setPreferredSize(new Dimension(540, myPost.height)); // 면적 잡아주고
		pnBg.add(myPost);
		pnBg.repaint();
	}
}
