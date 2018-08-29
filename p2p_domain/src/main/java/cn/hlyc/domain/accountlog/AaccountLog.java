package cn.hlyc.domain.accountlog;
// 测试触发
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "T_ACCOUNT_LOG")
public class AaccountLog {
	@Id
	@GeneratedValue()
	@Column(name = "A_ID")
	private Integer id; // 主键

	@Column(name = "A_AFTER_TRADING_MONEY")
	private Float afterMoney; // 交易后金额

	@Column(name = "A_AMOUNT")
	private Float amount; // 金额

	@Column(name = "A_BEFORE_TRADING_MONEY")
	private Float beforeMoney; // 交易前金额

	@Column(name = "A_CURRENT_PERIOD")
	private Integer currentPeriod; // 当前期

	@Column(name = "A_DATE")
	private Date date; // 交易时间

	@Column(name = "A_DESCREPTION")
	private String descreption; // 交易详情

	@Column(name = "A_MAIN_ACCOUNT_ID")
	private Integer accountId; // 主账户id

	@Column(name = "A_RECEIVE_OR_PAY")
	private Integer receiveOrPays; // 收付

	@Column(name = "A_TRANSFER_SERIAL_NO")
	private String lsh; // 交易流水号

	@Column(name = "A_TRANSFER_STATUS")
	private Integer status; // 交易状态

	@Column(name = "A_TYPE")
	private Integer type; // 交易类型

	@Column(name = "A_USER_ID")
	private Integer userId; // 用户id

	@Column(name = "P_ID")
	private Integer pid; // 投资记录ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(Float afterMoney) {
		this.afterMoney = afterMoney;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getBeforeMoney() {
		return beforeMoney;
	}

	public void setBeforeMoney(Float beforeMoney) {
		this.beforeMoney = beforeMoney;
	}

	public Integer getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Integer currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescreption() {
		return descreption;
	}

	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getReceiveOrPays() {
		return receiveOrPays;
	}

	public void setReceiveOrPays(Integer receiveOrPays) {
		this.receiveOrPays = receiveOrPays;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "AaccountLog [id=" + id + ", afterMoney=" + afterMoney + ", amount=" + amount + ", beforeMoney="
				+ beforeMoney + ", currentPeriod=" + currentPeriod + ", date=" + date + ", descreption=" + descreption
				+ ", accountId=" + accountId + ", receiveOrPays=" + receiveOrPays + ", lsh=" + lsh + ", status="
				+ status + ", type=" + type + ", userId=" + userId + ", pid=" + pid + "]";
	}

}
