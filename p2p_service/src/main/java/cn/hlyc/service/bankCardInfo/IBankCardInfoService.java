package cn.hlyc.service.bankCardInfo;

import cn.hlyc.domain.bankCardInfo.BankCardInfo;

public interface IBankCardInfoService {

	BankCardInfo findByUserId(Integer userId);

	void addBankCardInfo(BankCardInfo bci);

}
