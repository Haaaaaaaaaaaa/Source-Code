package cn.edu.ujn.database;

import java.io.Serializable;

public class User implements Serializable {
	// ���ֶα������
	private static final long serialVersionUID = 42L;

	private String workid; // ����
	private String name; // ����
	private String sex; // �Ա�
	private String phone; // �绰����

	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [workid=" + workid + ", name=" + name + ", sex=" + sex + ", phone=" + phone + "]";
	}
}
