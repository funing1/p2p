package cn.hlyc.service.privilege.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.privilege.PrivilegeDao;
import cn.hlyc.domain.privilege.Privilege;
import cn.hlyc.service.privilege.PrivilegeService;
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Autowired
	private PrivilegeDao privilegeDao;

	@Override
	public List<Privilege> findAll() {
		// TODO Auto-generated method stub
		return  privilegeDao.findAll();
	}

	@Override
	public Privilege findById(Integer privilegeId) {
		// TODO Auto-generated method stub
		return  privilegeDao.findOne(privilegeId);
	}

}
