package cn.hlyc.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.user.IUserDAO;
import cn.hlyc.domain.user.UserModel;
import cn.hlyc.service.user.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDao;

	@Override
	public UserModel findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public UserModel findByPhone(String phone) {
		return userDao.findByPhone(phone);
	}

	@Override
	public boolean addUser(UserModel user) {
		UserModel u = userDao.save(user);
		return u != null;
	}

	@Override
	public UserModel login(String username, String pwd) {
		return userDao.login(username, pwd);
	}

	@Override
	public UserModel findById(int userId) {
		return userDao.findOne(userId);
	}

	@Override
	public void updatePhoneStatus(String phone, int id) {
		userDao.updatePhoneStatus(phone, id);
	}

	@Override
	@Transactional
	public void updateRealName(String realName, String identity, int i) {

		userDao.updateRealNameStatus(realName, identity, i);
	}

	@Override
	@Transactional
	public void addEmail(String email, int id) {
		userDao.addEmail(email, id);
	}

	@Override
	@Transactional
	public void updateEmailStatus(int id) {
		
		userDao.updateEmailStatus(id);
	}

	@Override
	@Transactional
	public void updatePayPasswordAndPayPwdStatus(String payPassword, int id) {
		
		userDao.updatePayPasswordAndPayPwdStatus(payPassword, id);
		
	}

	@Override
	public List<UserModel> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	@Transactional
	public void delById(int parseInt) {
		// TODO Auto-generated method stub
		 userDao.delete(parseInt);
	}

	@Override
	@Transactional
	public void update(UserModel us) {
		// TODO Auto-generated method stub
		userDao.htupdate(us);
	}

	@Override
	public void updateImg(String uploadImageFileName,Integer userId) {
		// TODO Auto-generated method stub
		userDao.updateImg(uploadImageFileName,userId);
	}



}
