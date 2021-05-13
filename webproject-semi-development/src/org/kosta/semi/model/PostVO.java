package org.kosta.semi.model;

public class PostVO {
	private String postNo;
	private String postTitle;
	private String postContent;
	private int hits;
	private String postTime;
	private MemberVO memberVO;
	private CountryVO countryVO;
	private String catergory;
	private int comment_count;
	public PostVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostVO(String postNo, String postTitle, String postContent, int hits, String postTime, MemberVO memberVO,
			CountryVO countryVO, String catergory, int comment_count ) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.hits = hits;
		this.postTime = postTime;
		this.memberVO = memberVO;
		this.countryVO = countryVO;
		this.catergory = catergory;
		this.comment_count = comment_count;
	}
	
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public CountryVO getCountryVO() {
		return countryVO;
	}
	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}
	public String getCatergory() {
		return catergory;
	}
	public void setCatergory(String catergory) {
		this.catergory = catergory;
	}
	@Override
	public String toString() {
		return "PostVO [postNo=" + postNo + ", postTitle=" + postTitle + ", postContent=" + postContent + ", hits="
				+ hits + ", postTime=" + postTime + ", memberVO=" + memberVO + ", countryVO=" + countryVO
				+ ", catergory=" + catergory + ", comment_count=" + comment_count + "]";
	}
	
	
}
