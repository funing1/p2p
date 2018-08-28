package cn.hlyc.domain.purchase;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASE")
public class Purchase {
	@Id
	@GeneratedValue()
	@Column(name = "ID")
	private Integer id;

	// 当前用户id
	@Column(name = "USERID")
	private Integer userId;

	// 要购买的产品的名字
	@Column(name = "PNAME")
	private String pName;

	// 购买选择的方案
	@Column(name = "PPRODUCTID")
	private Integer pProductId;

	// 购买选择的本金
	@Column(name = "PAMOUNT")
	private Integer pAmount;

	// 购买选择的投资期限
	@Column(name = "PDEADLINE")
	private Integer pDeadline;

	// 利润
	@Column(name = "PEXPECTEDANNUALINCOME")
	private Double pExpectedAnnualIncome;

	// 预期收益
	@Column(name = "PMONTHINTEREST")
	private Double pMonthInterest;

	// 每月提取利息
	@Column(name = "PMONTHLYEXTRACTINTEREST")
	private Double pMonthlyExtractInterest;

	//交易日期
	@Column(name = "BUYTIME")
	private Date buyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public Integer getpProductId() {
		return pProductId;
	}

	public void setpProductId(Integer pProductId) {
		this.pProductId = pProductId;
	}

	public Integer getpAmount() {
		return pAmount;
	}

	public void setpAmount(Integer pAmount) {
		this.pAmount = pAmount;
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

	public Double getpMonthInterest() {
		return pMonthInterest;
	}

	public void setpMonthInterest(Double pMonthInterest) {
		this.pMonthInterest = pMonthInterest;
	}

	public Double getpMonthlyExtractInterest() {
		return pMonthlyExtractInterest;
	}

	public void setpMonthlyExtractInterest(Double pMonthlyExtractInterest) {
		this.pMonthlyExtractInterest = pMonthlyExtractInterest;
	}

	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Purchase(Integer userId, String pName, Integer pProductId, Integer pAmount, Integer pDeadline,
			Double pExpectedAnnualIncome, Double pMonthInterest, Double pMonthlyExtractInterest, Date buyTime) {
		super();
		this.userId = userId;
		this.pName = pName;
		this.pProductId = pProductId;
		this.pAmount = pAmount;
		this.pDeadline = pDeadline;
		this.pExpectedAnnualIncome = pExpectedAnnualIncome;
		this.pMonthInterest = pMonthInterest;
		this.pMonthlyExtractInterest = pMonthlyExtractInterest;
		this.buyTime = buyTime;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", userId=" + userId + ", pName=" + pName + ", pProductId=" + pProductId
				+ ", pAmount=" + pAmount + ", pDeadline=" + pDeadline + ", pExpectedAnnualIncome="
				+ pExpectedAnnualIncome + ", pMonthInterest=" + pMonthInterest + ", pMonthlyExtractInterest="
				+ pMonthlyExtractInterest + ", buyTime=" + buyTime + "]";
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

}
