package org.kosta.semi.model;

public class FileVO {
	private String id;
	private PostVO postVO;
	private String orgName;
	private String fileName;
	private String filePath;
	private String fileSize;
	private String fdate;
	public FileVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileVO(String id, PostVO postVO, String orgName, String fileName, String filePath, String fileSize,
			String fdate) {
		super();
		this.id = id;
		this.postVO = postVO;
		this.orgName = orgName;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.fdate = fdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PostVO getPostVO() {
		return postVO;
	}
	public void setPostVO(PostVO postVO) {
		this.postVO = postVO;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	@Override
	public String toString() {
		return "FileVO [id=" + id + ", postVO=" + postVO + ", orgName=" + orgName + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", fileSize=" + fileSize + ", fdate=" + fdate + "]";
	}
	
}
