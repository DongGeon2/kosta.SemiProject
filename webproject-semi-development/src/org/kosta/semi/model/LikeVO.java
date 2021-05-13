package org.kosta.semi.model;

public class LikeVO {
	private PostVO postVO;
	private MemberVO memberVO;
	public LikeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeVO(PostVO postVO, MemberVO memberVO) {
		super();
		this.postVO = postVO;
		this.memberVO = memberVO;
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
	@Override
	public String toString() {
		return "LikeVO [postVO=" + postVO + ", memberVO=" + memberVO + "]";
	}
}
