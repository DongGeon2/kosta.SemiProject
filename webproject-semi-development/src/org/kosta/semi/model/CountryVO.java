package org.kosta.semi.model.vo;

public class CountryVO {
	private String countryId;
	private String countryTime;
	private String language;
	private String currency;
	private String countryName;
	public CountryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CountryVO(String countryId, String countryTime, String language, String currency, String countryName) {
		super();
		this.countryId = countryId;
		this.countryTime = countryTime;
		this.language = language;
		this.currency = currency;
		this.countryName = countryName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryTime() {
		return countryTime;
	}
	public void setCountryTime(String countryTime) {
		this.countryTime = countryTime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Override
	public String toString() {
		return "CountryVO [countryId=" + countryId + ", countryTime=" + countryTime + ", language=" + language
				+ ", currency=" + currency + ", countryName=" + countryName + "]";
	}
	
	

}
