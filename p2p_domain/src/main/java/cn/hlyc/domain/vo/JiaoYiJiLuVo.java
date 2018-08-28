package cn.hlyc.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class JiaoYiJiLuVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date buyTime; // 投资交易时间

	private String username; // 用户名

	private String productName; // 产品名称

	private Integer pDeadline; // 投资期限

	private Double pExpectedAnnualIncome; // 利率

	private Integer pAmount; // 投资金额

	private int isRepeatInvest;// 是否复投

	private Integer pProductId; // 购买选择的方案

	private Integer userId; // 当前用户id

	private long proId;// 产品id

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getpDeadline() {
		return pDeadline;
	}

	public void setpDeadline(Integer pDeadline) {
		this.pDeadline = pDeadline;
	}

	public Double getpExpectedAnnualIncome() {
		return pExpectedAnnualIncome;
	}

	public void setpExpectedAnnualIncome(Double pExpectedAnnualIncome) {
		this.pExpectedAnnualIncome = pExpectedAnnualIncome;
	}

	public Integer getpAmount() {
		return pAmount;
	}

	public void setpAmount(Integer pAmount) {
		this.pAmount = pAmount;
	}

	public int getIsRepeatInvest() {
		return isRepeatInvest;
	}

	public void setIsRepeatInvest(int isRepeatInvest) {
		this.isRepeatInvest = isRepeatInvest;
	}

	public Integer getpProductId() {
		return pProductId;
	}

	public void setpProductId(Integer pProductId) {
		this.pProductId = pProductId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public long getProId() {
		return proId;
	}

	public void setProId(long proId) {
		this.proId = proId;
	}

	@Override
	public String toString() {
		return "JiaoYiJiLu [buyTime=" + buyTime + ", username=" + username + ", productName=" + productName
				+ ", pDeadline=" + pDeadline + ", pExpectedAnnualIncome=" + pExpectedAnnualIncome + ", pAmount="
				+ pAmount + ", isRepeatInvest=" + isRepeatInvest + ", pProductId=" + pProductId + ", userId=" + userId
				+ ", proId=" + proId + "]";
	}

}
