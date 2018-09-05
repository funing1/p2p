package cn.hlyc.service.role;

import java.util.List;

import cn.hlyc.domain.role.Role;

public interface RoleService {

	Role findOne(Integer roleId);

	List<Role> findAll();



}
