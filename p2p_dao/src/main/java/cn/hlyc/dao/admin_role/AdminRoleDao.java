package cn.hlyc.dao.admin_role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.adminRole.Admin_role;

public interface AdminRoleDao extends JpaRepository<Admin_role, Integer>{

	@Query("select ar.roleId from Admin_role ar where ar.adminId = ?1")
	List<Integer> findroleId(Integer id);

	@Modifying
	@Query("delete from Admin_role ar where ar.adminId=?1")
	void delByAdminId(Integer adminId);

	

}
