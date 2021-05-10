package org.kosta.semi.model;

public class CommentVO {
	private String commentNo;
	//private String postNo;
	private PostVO postVO;
	//private String countryId;
	private CountryVO countryVO;
	private String commentContent;
	private String CommentedTime;
	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentVO(String commentNo, PostVO postVO, CountryVO countryVO, String commentContent,
			String commentedTime) {
		super();
		this.commentNo = commentNo;
		this.postVO = postVO;
		this.countryVO = countryVO;
		this.commentContent = commentContent;
		CommentedTime = commentedTime;
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
	public CountryVO getCountryVO() {
		return countryVO;
	}
	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentedTime() {
		return CommentedTime;
	}
	public void setCommentedTime(String commentedTime) {
		CommentedTime = commentedTime;
	}
	@Override
	public String toString() {
		return "CommentVO [commentNo=" + commentNo + ", postVO=" + postVO + ", countryVO=" + countryVO
				+ ", commentContent=" + commentContent + ", CommentedTime=" + CommentedTime + "]";
	}
	
}
