package org.kosta.semi.model;

public class LikeVO {
	private String likeId;
	private PostVO postVO;
	private FileVO fileVO;
	private String likeCount;
	public LikeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeVO(String likeId, PostVO postVO, FileVO fileVO, String likeCount) {
		super();
		this.likeId = likeId;
		this.postVO = postVO;
		this.fileVO = fileVO;
		this.likeCount = likeCount;
	}
	public String getLikeId() {
		return likeId;
	}
	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}
	public PostVO getPostVO() {
		return postVO;
	}
	public void setPostVO(PostVO postVO) {
		this.postVO = postVO;
	}
	public FileVO getFileVO() {
		return fileVO;
	}
	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(String likeCount) {
		this.likeCount = likeCount;
	}
	@Override
	public String toString() {
		return "LikeVO [likeId=" + likeId + ", postVO=" + postVO + ", fileVO=" + fileVO + ", likeCount=" + likeCount
				+ "]";
	}
	 
}
