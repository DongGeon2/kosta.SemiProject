package org.kosta.semi.model;

public class PostCommentVO {
	private String commentNo;
	private String postNo;
	private String id;
	private String content;
	private String timeCommented;
	public PostCommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostCommentVO(String commentNo, String postNo, String id, String content, String timeCommented) {
		super();
		this.commentNo = commentNo;
		this.postNo = postNo;
		this.id = id;
		this.content = content;
		this.timeCommented = timeCommented;
	}
	public String getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimeCommented() {
		return timeCommented;
	}
	public void setTimeCommented(String timeCommented) {
		this.timeCommented = timeCommented;
	}
	@Override
	public String toString() {
		return "PostCommentVO [commentNo=" + commentNo + ", postNo=" + postNo + ", id=" + id + ", content=" + content
				+ ", timeCommented=" + timeCommented + "]";
	}
}
