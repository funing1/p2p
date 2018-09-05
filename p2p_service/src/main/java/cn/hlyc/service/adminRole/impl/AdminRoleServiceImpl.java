package cn.hlyc.service.adminRole.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.admin_role.AdminRoleDao;
import cn.hlyc.domain.adminRole.Admin_role;
import cn.hlyc.service.adminRole.AdminRoleService;
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
	
	
	@Autowired
	private AdminRoleDao adminRoleDao;

	@Override
	public List<Integer> findroleId(Integer id) {
		// TODO Auto-generated method stub
		return adminRoleDao.findroleId(id);
	}

	@Transactional
	@Override
	public void delByAdminId(Integer adminId) {
		adminRoleDao.delByAdminId(adminId);
		
	}

	@Transactional
	@Override
	public void saveRole(Admin_role admin_role) {
		adminRoleDao.save(admin_role);
	}

	

}
