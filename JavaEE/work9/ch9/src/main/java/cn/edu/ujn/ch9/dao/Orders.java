package cn.edu.ujn.ch9.dao;

import java.util.List;

public class Orders {
	private Integer id;

	private String number;

	private Integer userId;

	private List<Product> pruductList;

	public List<Product> getPruductList() {
		return pruductList;
	}

	public void setPruductList(List<Product> pruductList) {
		this.pruductList = pruductList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", number=" + number + ", userId=" + userId + ", pruductList=" + pruductList + "]";
	}
}