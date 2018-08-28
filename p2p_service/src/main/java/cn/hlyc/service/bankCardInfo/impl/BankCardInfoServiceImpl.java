package cn.hlyc.service.bankCardInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.bankCardInfo.IBankCardInfoDAO;
import cn.hlyc.domain.bankCardInfo.BankCardInfo;
import cn.hlyc.service.bankCardInfo.IBankCardInfoService;

@Service
@Transactional
public class BankCardInfoServiceImpl implements IBankCardInfoService {

	@Autowired
	private IBankCardInfoDAO bankCardDao;

	@Override
	public BankCardInfo findByUserId(Integer userId) {
		return bankCardDao.findByUserId(userId);
	}

	@Override
	public void addBankCardInfo(BankCardInfo bci) {
		bankCardDao.save(bci);
	}
}
