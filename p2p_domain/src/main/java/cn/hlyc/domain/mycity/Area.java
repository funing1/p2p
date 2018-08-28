package cn.hlyc.domain.mycity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AREA")
public class Area {
	@Id
	@GeneratedValue()
	@Column(name = "RID")
	private Integer cityId;// 城市表 主键

	@Column(name = "AREAID")
	private String cityAreaNum;// 城市编号

	@Column(name = "AREA")
	private String cityName;// 城市名称

	@Column(name = "FATHER")
	@JsonIgnore
	private String parentCityAreaNum;// 父级城市编号

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

	public String getParentCityAreaNum() {
		return parentCityAreaNum;
	}

	public void setParentCityAreaNum(String parentCityAreaNum) {
		this.parentCityAreaNum = parentCityAreaNum;
	}

	@Override
	public String toString() {
		return "Area [cityId=" + cityId + ", cityAreaNum=" + cityAreaNum + ", cityName=" + cityName
				+ ", parentCityAreaNum=" + parentCityAreaNum + "]";
	}

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Area(Integer cityId, String cityAreaNum, String cityName, String parentCityAreaNum) {
		super();
		this.cityId = cityId;
		this.cityAreaNum = cityAreaNum;
		this.cityName = cityName;
		this.parentCityAreaNum = parentCityAreaNum;
	}

}
