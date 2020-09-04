package myMenu;

public class PostDTO {
	private int pNo;
	private String pImg;
	private String pCon;
	private String pCDate;
	private String pUDate;
	private int pLike;
	private String uID;
	
	public PostDTO() {
		super();
	}
	public PostDTO(int pNo, String pImg, String pCon, String pCDate, String pUDate, int pLike, String uID) {
		super();
		this.pNo = pNo;
		this.pImg = pImg;
		this.pCon = pCon;
		this.pCDate = pCDate;
		this.pUDate = pUDate;
		this.pLike = pLike;
		this.uID = uID;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getpImg() {
		return pImg;
	}
	public void setpImg(String pImg) {
		this.pImg = pImg;
	}
	public String getpCon() {
		return pCon;
	}
	public void setpCon(String pCon) {
		this.pCon = pCon;
	}
	public String getpCDate() {
		return pCDate;
	}
	public void setpCDate(String pCDate) {
		this.pCDate = pCDate;
	}
	public String getpUDate() {
		return pUDate;
	}
	public void setpUDate(String pUDate) {
		this.pUDate = pUDate;
	}
	public int getpLike() {
		return pLike;
	}
	public void setpLike(int pLike) {
		this.pLike = pLike;
	}
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	@Override
	public String toString() {
		return pNo + "," + pImg + "," + pCon + "," + pCDate + "," + pUDate
				+ "," + pLike + "," + uID;
	}
}
