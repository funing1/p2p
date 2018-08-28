package cn.hlyc.domain.vo;

import cn.hlyc.domain.purchase.Purchase;

public class PurchaseVo {
	private String pname;

	private Long zmoney;

	private double count;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Long getZmoney() {
		return zmoney;
	}

	public void setZmoney(Long zmoney) {
		this.zmoney = zmoney;
	}

	

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PurchaseVo [pname=" + pname + ", zmoney=" + zmoney + ", count=" + count + "]";
	}

	public PurchaseVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseVo(String pname, Long zmoney) {
		super();
		this.pname = pname;
		this.zmoney = zmoney;
	}

	public PurchaseVo(String pname, Long zmoney, double count) {
		super();
		this.pname = pname;
		this.zmoney = zmoney;
		this.count = count;
	}

	
}
