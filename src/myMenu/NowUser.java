package myMenu;

public class NowUser {
	private static String loginID = "bbbb";
	
	public static void setloginID(String loginID) {
		NowUser.loginID = loginID;
	}
	public static String getloginID() {
		return loginID;
	}
}
