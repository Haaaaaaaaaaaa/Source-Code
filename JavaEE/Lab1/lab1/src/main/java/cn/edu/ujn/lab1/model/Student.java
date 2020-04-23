package cn.edu.ujn.lab1.model;

import org.springframework.stereotype.Component;

@Component
public class Student {
	
	private int uid;
	private String stunumber;
	private String stuname;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getStunumber() {
		return stunumber;
	}
	public void setStunumber(String stunumber) {
		this.stunumber = stunumber;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	
	@Override
	public String toString() {
		return "Student [uid=" + uid + ", stunumber=" + stunumber + ", stuname=" + stuname + "]";
	}
}
