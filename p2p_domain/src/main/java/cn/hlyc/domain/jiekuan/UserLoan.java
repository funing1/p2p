package cn.hlyc.domain.jiekuan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_LOAN")
public class UserLoan {

	@Id
	@GeneratedValue()
	@Column(name = "T_ID")
	private Integer id;

	@Column(name = "T_LOANNAME")
	private String lonaName; // 借款人姓名

	@Column(name = "T_SEX")
	private Integer sex; // 借款人性别

	@Column(name = "T_LOANMONEY")
	private double loanMoney; // 借款人借款数目

	@Column(name = "T_PHONE")
	private String phone; // 借款人电话

	@Column(name = "T_ADDRESS")
	private Integer address; // 借款人地址

	@Column(name = "T_USERID")
	private Integer userId; // 借款用户ID

	@Column(name = "TIME")
	private Date time; // 借款用户ID

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLonaName() {
		return lonaName;
	}

	public void setLonaName(String lonaName) {
		this.lonaName = lonaName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public double getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(double loanMoney) {
		this.loanMoney = loanMoney;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserLoan [id=" + id + ", lonaName=" + lonaName + ", sex=" + sex + ", loanMoney=" + loanMoney
				+ ", phone=" + phone + ", address=" + address + ", userId=" + userId + ", time=" + time + "]";
	}

	public UserLoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoan(String lonaName, Integer sex, double loanMoney, String phone, Integer address, Integer userId,
			Date time) {
		super();
		this.lonaName = lonaName;
		this.sex = sex;
		this.loanMoney = loanMoney;
		this.phone = phone;
		this.address = address;
		this.userId = userId;
		this.time = time;
	}

}
