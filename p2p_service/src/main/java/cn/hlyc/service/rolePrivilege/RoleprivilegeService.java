package cn.hlyc.service.rolePrivilege;

import java.util.List;

import cn.hlyc.domain.rolePrivilege.Role_Privilege;

public interface RoleprivilegeService {

	List<Integer> findPrivilegeId(Integer roleId);

	void delByRoleId(Integer roleId);

	void saveRolePrivilege(Role_Privilege role_Privilege);

}
