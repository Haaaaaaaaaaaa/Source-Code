package model;

public class User {
	int id;
	String username;
	String password;
//	存放列名
	String []columnName;
//	存放记录
	String [][]tableRecord=null;
	
	public String[] getColumnName() {
		return columnName;
	}
	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}
	public String[][] getTableRecord() {
		return tableRecord;
	}
	public void setTableRecord(String[][] tableRecord) {
		this.tableRecord = tableRecord;
	}
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
