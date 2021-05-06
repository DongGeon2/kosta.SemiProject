package org.kosta.semi.model.vo;

public class ManagerVO {
	private String id;
	private String password;
	private String name;
	public ManagerVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ManagerVO [id=" + id + ", password=" + password + ", name=" + name + "]";
	}
}
