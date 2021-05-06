package org.kosta.semi.model.vo;

public class MemberVO {
	private CountryVO countryVO;
	private String birth;
	private String gender;
	private String travelStyle;
	private String email;
	private String id;
	private String name;
	private String password;
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(CountryVO countryVO, String birth, String gender, String travelStyle, String email, String id,
			String name, String password) {
		super();
		this.countryVO = countryVO;
		this.birth = birth;
		this.gender = gender;
		this.travelStyle = travelStyle;
		this.email = email;
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public CountryVO getCountryVO() {
		return countryVO;
	}
	public void setCountryVO(CountryVO countryVO) {
		this.countryVO = countryVO;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTravelStyle() {
		return travelStyle;
	}
	public void setTravelStyle(String travelStyle) {
		this.travelStyle = travelStyle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "MemberVO [countryVO=" + countryVO + ", birth=" + birth + ", gender=" + gender + ", travelStyle="
				+ travelStyle + ", email=" + email + ", id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
