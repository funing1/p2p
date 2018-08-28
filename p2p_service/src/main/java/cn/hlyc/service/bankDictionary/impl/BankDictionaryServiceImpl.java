package cn.hlyc.service.bankDictionary.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.bankDictionary.IBankDictionaryDao;
import cn.hlyc.domain.bankCardInfo.BankDictionary;
import cn.hlyc.service.bankDictionary.IBankDictionaryService;
@Service
public class BankDictionaryServiceImpl implements IBankDictionaryService {
	@Autowired
	private IBankDictionaryDao bankDictionaryDao;
	
	@Override
	public List<BankDictionary> findAll() {
		// TODO Auto-generated method stub
		return bankDictionaryDao.findAll();
	}

}
