package assignment.Model;

public class LoginBody {
	
	
	String login_id;
	String password;
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginBody(String login_id, String password) {
		super();
		this.login_id = login_id;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [login_id=" + login_id + ", password=" + password + "]";
	}
	
}
