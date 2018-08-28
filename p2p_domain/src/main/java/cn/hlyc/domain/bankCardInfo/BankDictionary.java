package cn.hlyc.domain.bankCardInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BANKDICTIONARY")
public class BankDictionary {
	@Id
	@GeneratedValue()
	@Column(name = "t_id")
	private Integer bankId;// 主键

	@Column(name = "t_name")
	private String bankName;// 银行名称

	@Column(name = "t_value")
	private Integer bankValue;// 银行编号

	@Override
	public String toString() {
		return "BankDictionary [bankId=" + bankId + ", bankName=" + bankName + ", bankValue=" + bankValue + "]";
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getBankValue() {
		return bankValue;
	}

	public void setBankValue(Integer bankValue) {
		this.bankValue = bankValue;
	}

	public BankDictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankDictionary(Integer bankId, String bankName, Integer bankValue) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankValue = bankValue;
	}


}
