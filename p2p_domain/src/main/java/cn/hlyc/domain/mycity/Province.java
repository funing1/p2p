package cn.hlyc.domain.mycity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROVINCE")
public class Province {
	@Id
	@GeneratedValue()
	@Column(name = "SID")
	private Integer cityId;// 城市表 主键

	@Column(name = "PROVINCEID")
	private String cityAreaNum;// 城市编号

	@Column(name = "PROVINCE")
	private String cityName;// 城市名称

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityAreaNum() {
		return cityAreaNum;
	}

	public void setCityAreaNum(String cityAreaNum) {
		this.cityAreaNum = cityAreaNum;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "Province [cityId=" + cityId + ", cityAreaNum=" + cityAreaNum + ", cityName=" + cityName + "]";
	}

	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Province(Integer cityId, String cityAreaNum, String cityName) {
		super();
		this.cityId = cityId;
		this.cityAreaNum = cityAreaNum;
		this.cityName = cityName;
	}

}
