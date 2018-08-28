package cn.hlyc.service.admin.impl;

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

}
