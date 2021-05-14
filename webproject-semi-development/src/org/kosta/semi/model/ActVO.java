package org.kosta.semi.model;

public class ActVO {
	private String memberId;
	private String actedTime;
	private int point;
	private String message;
	
	public ActVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ActVO(String member_id, String acted_time, String message) {
		super();
		this.memberId = member_id;
		this.actedTime = acted_time;
		this.message = message;
	}

	public ActVO(String member_id, String acted_time, int point, String message) {
		super();
		this.memberId = member_id;
		this.actedTime = acted_time;
		this.point = point;
		this.message = message;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getActedTime() {
		return actedTime;
	}

	public void setActedTime(String actedTime) {
		this.actedTime = actedTime;
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
		return "ActVO [member_id=" + memberId + ", acted_time=" + actedTime + ", point=" + point + ", message="
				+ message + "]";
	}
	
}
