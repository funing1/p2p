package cn.hlyc.dao.userAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.userAccount.UserAccountModel;

public interface IUserAccountDAO extends JpaRepository<UserAccountModel, Integer> {

	public UserAccountModel findByUserId(int userId);

	@Modifying
	@Query("update UserAccountModel u set u.balance= u.balance + ?2 , u.total= u.total + ?2 where u.userId=?1")
	public void updateAddByUserId(int userId, double loanMoney);
	
	
	@Modifying
	@Query("update UserAccountModel u set u.balance= u.balance - ?2 , u.total= u.total - ?2 where u.userId=?1")
	public void updateSubtractionByUserId(int userId, double loanMoney);
}
