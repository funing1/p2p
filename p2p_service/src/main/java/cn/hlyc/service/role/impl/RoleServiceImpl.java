package cn.hlyc.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.role.RoleDao;
import cn.hlyc.domain.role.Role;
import cn.hlyc.service.role.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findOne(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.findOne(roleId);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	

}
