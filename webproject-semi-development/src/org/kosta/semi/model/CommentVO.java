package org.kosta.semi.model;

public class CommentVO {
	private String commentNo;
	//private String postNo;
	private PostVO postVO;
	//private String id;
	private MemberVO memberVO;
	private String commentContent;
	private String commentedTime;
	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentVO(String commentNo, PostVO postVO, MemberVO memberVO, String commentContent, String commentedTime) {
		super();
		this.commentNo = commentNo;
		this.postVO = postVO;
		this.memberVO = memberVO;
		this.commentContent = commentContent;
		this.commentedTime = commentedTime;
	}
	public String getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}
	public PostVO getPostVO() {
		return postVO;
	}
	public void setPostVO(PostVO postVO) {
		this.postVO = postVO;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentedTime() {
		return commentedTime;
	}
	public void setCommentedTime(String commentedTime) {
		this.commentedTime = commentedTime;
	}
	@Override
	public String toString() {
		return "CommentVO [commentNo=" + commentNo + ", postVO=" + postVO + ", memberVO=" + memberVO
				+ ", commentContent=" + commentContent + ", commentedTime=" + commentedTime + "]";
	}
	
}
