package org.kosta.semi.model;
//보기 항목으로 인덱스를 넣어주기로
public class StyleVO {
	private String style1;
	private String style2;
	private String style3;
	private String style4;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public StyleVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StyleVO(String style1, String style2, String style3, String style4) {
		super();
		this.style1 = style1;
		this.style2 = style2;
		this.style3 = style3;
		this.style4 = style4;
	}
	public StyleVO(String style1, String style2, String style3, String style4, String message) {
		this.style1 = style1;
		this.style2 = style2;
		this.style3 = style3;
		this.style4 = style4;
		this.message=message;
	}
	public String getStyle1() {
		return style1;
	}
	public void setStyle1(String style1) {
		this.style1 = style1;
	}
	public String getStyle2() {
		return style2;
	}
	public void setStyle2(String style2) {
		this.style2 = style2;
	}
	public String getStyle3() {
		return style3;
	}
	public void setStyle3(String style3) {
		this.style3 = style3;
	}
	public String getStyle4() {
		return style4;
	}
	public void setStyle4(String style4) {
		this.style4 = style4;
	}
	@Override
	public String toString() {
		return style1+","+style2 + ","+style3+","+style4;
	}
}
