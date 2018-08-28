package cn.hlyc.service.charges;

import java.util.Date;
import java.util.List;

import cn.hlyc.domain.purchase.Purchase;
import cn.hlyc.domain.vo.JiaoYiJiLuVo;
import cn.hlyc.domain.vo.PurchaseVo;

public interface IChargesService {

	void add(Integer userId, String pName, int parseInt, int parseInt2, int parseInt3, Double parseInt4, Double parseInt5,
			Double parseInt6);

	List<JiaoYiJiLuVo> findAll();

	List<PurchaseVo> chaxunGroupByPname();

	List<PurchaseVo> countProduct();

	List<PurchaseVo> betweenRiQi(Date parse, Date parse2);

}
