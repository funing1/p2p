package cn.hlyc.dao.bankDictionary;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hlyc.domain.bankCardInfo.BankDictionary;

public interface IBankDictionaryDao extends JpaRepository<BankDictionary, Integer>{

	
	
}
