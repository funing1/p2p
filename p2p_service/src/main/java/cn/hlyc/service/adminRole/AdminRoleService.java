package cn.hlyc.service.adminRole;

import java.util.List;

import cn.hlyc.domain.adminRole.Admin_role;

public interface AdminRoleService {

	List<Integer> findroleId(Integer id);

	void delByAdminId(Integer adminId);


	void saveRole(Admin_role admin_role);

}
