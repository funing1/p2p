package cn.hlyc.service.bank.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.bank.IBankDAO;
import cn.hlyc.domain.bankCardInfo.Bank;
import cn.hlyc.service.bank.IBankService;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	private IBankDAO bankDao;

	@Override
	public List<Bank> findAll() {
		return bankDao.findAll();
	}

	@Override
	public List<Bank> findDistinctBank() {
		// TODO Auto-generated method stub
		return bankDao.findDistinctBank();
	}

}
