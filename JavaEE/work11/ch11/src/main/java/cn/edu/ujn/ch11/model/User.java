package cn.edu.ujn.ch11.model;

public class User {
	private int id;
	private String naername;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaername() {
		return naername;
	}

	public void setNaername(String naername) {
		this.naername = naername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", naername=" + naername + ", password=" + password + "]";
	}

}
