package cn.hlyc.service.admin;

import cn.hlyc.domain.admin.AdminModel;

public interface IAdminService {

	public AdminModel login(String username,String password);
}
