package cn.hlyc.dao.rolePrivilege;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.role.Role;
import cn.hlyc.domain.rolePrivilege.Role_Privilege;

public interface RolePrivilegeDao extends JpaRepository<Role_Privilege, Integer>{
	@Query("select rp.privilegeid from Role_Privilege rp where rp.roleid = ?1")
	List<Integer> findPrivilegeId(Integer roleId);

	@Modifying
	@Query("delete from Role_Privilege rp where rp.roleid=?1")
	void delByRoleId(Integer roleId);

}
