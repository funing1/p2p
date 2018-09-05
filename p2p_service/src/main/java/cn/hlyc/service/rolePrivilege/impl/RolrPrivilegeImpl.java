package cn.hlyc.service.rolePrivilege.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.rolePrivilege.RolePrivilegeDao;
import cn.hlyc.domain.rolePrivilege.Role_Privilege;
import cn.hlyc.service.rolePrivilege.RoleprivilegeService;
@Service
public class RolrPrivilegeImpl implements RoleprivilegeService {
	
	@Autowired
	private RolePrivilegeDao rolePrivilegeDao;

	@Override
	public List<Integer> findPrivilegeId(Integer roleId) {
		// TODO Auto-generated method stub
		return rolePrivilegeDao.findPrivilegeId(roleId);
	}

	@Override
	@Transactional
	public void delByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		rolePrivilegeDao.delByRoleId(roleId);
	}

	@Override
	@Transactional
	public void saveRolePrivilege(Role_Privilege role_Privilege) {
		rolePrivilegeDao.save(role_Privilege);
		
	}

}
