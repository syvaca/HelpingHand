package usc.teamfive.helpinghand.datastructures;

public class User {
	
	// only setting up variables we absolutely need (time-crunch)
	private String name;
	private String email;
	private String phone;
	private String sid;
	
	// constructor
	public User(String name, String email, String phone, String sid) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.sid = sid;
	}

	// getters
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getSid() {
		return sid;
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
