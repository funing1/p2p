package cn.hlyc.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.admin.AdminDAO;
import cn.hlyc.domain.admin.AdminModel;
import cn.hlyc.service.admin.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminDAO adminDao;

	public AdminModel login(String username, String password) {
		return adminDao.login(username, password);
	}

	@Override
	public AdminModel findById(Integer adminId) {
		// TODO Auto-generated method stub
		return adminDao.findOne(adminId);
	}

	@Override
	public List<AdminModel> findAll() {
		// TODO Auto-generated method stub
		return adminDao.findAll();
	}

}
