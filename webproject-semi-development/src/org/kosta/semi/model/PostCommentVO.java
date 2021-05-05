package org.kosta.semi.model;

public class PostCommentVO {
	private String comment_no;
	private String post_no;
	private String member_id;
	private String content;
	private String time_commented;
	public PostCommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostCommentVO(String comment_no, String post_no, String member_id, String content, String time_commented) {
		super();
		this.comment_no = comment_no;
		this.post_no = post_no;
		this.member_id = member_id;
		this.content = content;
		this.time_commented = time_commented;
	}
	public String getComment_no() {
		return comment_no;
	}
	public void setComment_no(String comment_no) {
		this.comment_no = comment_no;
	}
	public String getPost_no() {
		return post_no;
	}
	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime_commented() {
		return time_commented;
	}
	public void setTime_commented(String time_commented) {
		this.time_commented = time_commented;
	}
	@Override
	public String toString() {
		return "PostCommentVO [comment_no=" + comment_no + ", post_no=" + post_no + ", member_id=" + member_id
				+ ", content=" + content + ", time_commented=" + time_commented + "]";
	}
}
