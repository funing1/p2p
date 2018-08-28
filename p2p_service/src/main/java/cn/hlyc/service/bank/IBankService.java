package cn.hlyc.service.bank;

import java.util.List;

import cn.hlyc.domain.bankCardInfo.Bank;

public interface IBankService {

	List<Bank> findAll();

	List<Bank> findDistinctBank();

}
