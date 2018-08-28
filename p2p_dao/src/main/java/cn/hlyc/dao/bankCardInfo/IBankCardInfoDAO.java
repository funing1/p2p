package cn.hlyc.dao.bankCardInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hlyc.domain.bankCardInfo.BankCardInfo;

public interface IBankCardInfoDAO extends JpaRepository<BankCardInfo, Integer>{

	BankCardInfo findByUserId(Integer userId);

}
