package cn.hlyc.service.charges.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.charges.IChargesDao;
import cn.hlyc.dao.product.IProductDAO;
import cn.hlyc.dao.user.IUserDAO;
import cn.hlyc.domain.product.Product;
import cn.hlyc.domain.purchase.Purchase;
import cn.hlyc.domain.user.UserModel;
import cn.hlyc.domain.vo.JiaoYiJiLuVo;
import cn.hlyc.domain.vo.PurchaseVo;
import cn.hlyc.service.charges.IChargesService;
import cn.hlyc.service.userAccount.IUserAccountService;

@Service
public class ChargesServiceImp implements IChargesService {

	@Autowired
	private IChargesDao chargesDao;

	@Autowired
	private IUserDAO iUserDAO;

	@Autowired
	private IProductDAO iProductDAO;

	@Override
	@Transactional
	public void add(Integer userId, String pName, int parseInt, int parseInt2, int parseInt3, Double parseInt4,
			Double parseInt5, Double parseInt6) {
		// TODO Auto-generated method stub
		chargesDao.save(new Purchase(userId, pName, parseInt, parseInt2, parseInt3, parseInt4, parseInt5, parseInt6,
				new Date()));
	}

	@Override
	public List<JiaoYiJiLuVo> findAll() {
		// TODO Auto-generated method stub

		List<JiaoYiJiLuVo> jyjl = new ArrayList<>();

		List<Purchase> PurchaseAll = chargesDao.findAll();

		for (Purchase purchase : PurchaseAll) {
			JiaoYiJiLuVo jy = new JiaoYiJiLuVo();
			jy.setBuyTime(purchase.getBuyTime());
			UserModel userModel = iUserDAO.findOne(purchase.getUserId());
			jy.setUserId(userModel.getId());
			jy.setUsername(userModel.getUsername());
			jy.setProductName(purchase.getpName());
			jy.setpDeadline(purchase.getpDeadline());
			jy.setpExpectedAnnualIncome(purchase.getpExpectedAnnualIncome());
			jy.setpAmount(purchase.getpAmount());
			Product product = iProductDAO.findByProductName(purchase.getpName());
			jy.setIsRepeatInvest(product.getIsRepeatInvest());
			jy.setpProductId(purchase.getpProductId());
			jy.setProId(product.getProId());
			jyjl.add(jy);
		}

		return jyjl;
	}

	@Override
	public List<PurchaseVo> chaxunGroupByPname() {
		// TODO Auto-generated method stub
		return chargesDao.chaxunGroupByPname();
	}

	@Override
	public List<PurchaseVo> countProduct() {
		// TODO Auto-generated method stub
		return chargesDao.countProduct();
	}

	@Override
	public List<PurchaseVo> betweenRiQi(Date parse, Date parse2) {
		// TODO Auto-generated method stub
		return chargesDao.betweenRiQi(parse, parse2);
	}

}
