package dto;

public class LoginView {
	
	private String userCode;
	private String id;
	
	public LoginView() {}
	
	public LoginView(String userCode, String id) {
		this.userCode = userCode;
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoginView [userCode=" + userCode + ", id=" + id + "]";
	}
	
	

}
