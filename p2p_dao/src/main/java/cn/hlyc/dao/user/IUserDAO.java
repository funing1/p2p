package cn.hlyc.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.hlyc.domain.user.UserModel;

public interface IUserDAO extends JpaRepository<UserModel, Integer> {

	UserModel findByUsername(String username);

	UserModel findByPhone(String phone);

	@Query("select u from UserModel u where u.username=?1 and u.password=?2")
	UserModel login(String username, String pwd);

	// 修改用户的手机号与手机状态
	@Modifying
	@Query("update UserModel u set u.phone=?1,u.phoneStatus=1 where u.id=?2")
	void updatePhoneStatus(String phone, int id);

	@Modifying
	@Query("update UserModel u set u.realName=?1,u.identity=?2,u.realNameStatus=1 where u.id=?3")
	void updateRealNameStatus(String realName, String identity, int i);

	@Modifying
	@Query("update UserModel u set u.email=?1 where u.id=?2")
	void addEmail(String email, int id);

	@Modifying
	@Query("update UserModel u set u.emailStatus=1 where u.id=?1")
	void updateEmailStatus(int id);

	@Modifying
	@Query("update UserModel u set u.payPassword=?1 ,u.payPwdStatus = 1 where u.id=?2")
	void updatePayPasswordAndPayPwdStatus(String payPassword, int id);

	@Modifying
	@Query("update UserModel u set u.username= :#{#us.username} ,u.password = :#{#us.password},u.email = :#{#us.email},u.phone = :#{#us.phone},u.registerTime = :#{#us.registerTime},u.emailStatus = :#{#us.emailStatus},u.phoneStatus = :#{#us.phoneStatus} where u.id= :#{#us.id}")
	void htupdate(@Param("us") UserModel us);

	
	@Modifying
	@Query("update UserModel u set u.userImg = ?1  where u.id= ?2")
	void updateImg(String uploadImageFileName,Integer userId);

	// UserModel findByUsernameAndPassword(String username,String password);

}
