package cn.hlyc.service.userAccount.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.userAccount.IUserAccountDAO;
import cn.hlyc.domain.userAccount.UserAccountModel;
import cn.hlyc.service.userAccount.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	@Autowired
	private IUserAccountDAO userAccountDao;

	@Override
	public void add(int id) {
		UserAccountModel uam = new UserAccountModel();
		uam.setUserId(id);
		userAccountDao.save(uam);
	}

	@Override
	public UserAccountModel findByUserId(int userId) {
		return userAccountDao.findByUserId(userId);
	}

	@Override
	@Transactional
	public void updateByUserId(int userId, double loanMoney) {
		userAccountDao.updateAddByUserId(userId, loanMoney);

	}

	@Override
	@Transactional
	public void updateSubtraction(Integer userId, double parseDouble) {
		// TODO Auto-generated method stub
		userAccountDao.updateSubtractionByUserId(userId, parseDouble);
		
	}

}
