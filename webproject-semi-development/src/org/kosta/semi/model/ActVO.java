package org.kosta.semi.model;

public class ActVO {
	private String member_id;
	private String acted_time;
	private int point;
	private String message;
	
	public ActVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ActVO(String member_id, String acted_time, String message) {
		super();
		this.member_id = member_id;
		this.acted_time = acted_time;
		this.message = message;
	}

	public ActVO(String member_id, String acted_time, int point, String message) {
		super();
		this.member_id = member_id;
		this.acted_time = acted_time;
		this.point = point;
		this.message = message;
	}

	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getActed_time() {
		return acted_time;
	}
	public void setActed_time(String acted_time) {
		this.acted_time = acted_time;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ActVO [member_id=" + member_id + ", acted_time=" + acted_time + ", point=" + point + ", message="
				+ message + "]";
	}
	
}
