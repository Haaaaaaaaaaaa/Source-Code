package cn.edu.ujn.ch4.jdbc;

public class Account {
	private int id;
	private String username;
	private double balance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
//	便于测试
	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", balance=" + balance + "]";
	}
	

}
