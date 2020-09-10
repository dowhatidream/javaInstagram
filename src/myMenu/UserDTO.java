package myMenu;

public class UserDTO {
	private String uID;
	private String uPW;
	private String uPhone;
	private String uCDate;
	
	public UserDTO() {
		super();
	}
	public UserDTO(String uID, String uPW, String uPhone, String uCDate) {
		super();
		this.uID = uID;
		this.uPW = uPW;
		this.uPhone = uPhone;
		this.uCDate = uCDate;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getuPW() {
		return uPW;
	}
	public void setuPW(String uPW) {
		this.uPW = uPW;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuCDate() {
		return uCDate;
	}
	public void setuCDate(String uCDate) {
		this.uCDate = uCDate;
	}
	
	@Override
	public String toString() {
		return "User [uID=" + uID + ", uPW=" + uPW + ", uPhone=" + uPhone + ", uCDate=" + uCDate + "]";
	}
}
