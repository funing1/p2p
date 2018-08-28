package cn.hlyc.service.userLoan.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.userLoan.IUserLoanDao;
import cn.hlyc.domain.jiekuan.UserLoan;
import cn.hlyc.domain.purchase.Purchase;
import cn.hlyc.service.userLoan.IUserLoanService;
@Service
public class UserLoanServiceImpl implements IUserLoanService {
	
	@Autowired
	private IUserLoanDao userLoanDao;

	@Override
	@Transactional
	public void save(UserLoan userLoan) {
		// TODO Auto-generated method stub
		userLoanDao.save(userLoan);
	}

	@Override
	@Transactional
	public void update(UserLoan u) {
		// TODO Auto-generated method stub
		userLoanDao.save(u);
	}

	

}
