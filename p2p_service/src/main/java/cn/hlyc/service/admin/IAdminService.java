package cn.hlyc.service.admin;

import java.util.List;

import cn.hlyc.domain.admin.AdminModel;

public interface IAdminService {

	public AdminModel login(String username,String password);

	public AdminModel findById(Integer adminId);

	public List<AdminModel> findAll();
}
