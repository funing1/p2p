package cn.hlyc.service.bankDictionary;

import java.util.List;

import cn.hlyc.domain.bankCardInfo.Bank;
import cn.hlyc.domain.bankCardInfo.BankDictionary;

public interface IBankDictionaryService {

	List<BankDictionary> findAll();

}
