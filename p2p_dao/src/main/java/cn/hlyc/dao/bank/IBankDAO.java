package cn.hlyc.dao.bank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.bankCardInfo.Bank;

public interface IBankDAO extends JpaRepository<Bank, Integer> {
	@Query("select bank from Bank bank where bank.bankId in (select min(bank.bankId) from Bank bank group by bank.bankName )")
	List<Bank> findDistinctBank();


}
