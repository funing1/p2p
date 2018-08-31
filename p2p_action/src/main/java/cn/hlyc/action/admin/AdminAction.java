package cn.hlyc.action.admin;

import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.domain.admin.AdminModel;
import cn.hlyc.service.admin.IAdminService;

@Controller
@Namespace("/account")
@Scope("prototype")
public class AdminAction extends BaseAction {
    
	//再次修改
	//再次修改
	@Autowired
	private IAdminService adminService;

	@Action("login")
	public void login() {
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		try {
			AdminModel admin = adminService.login(username, password);
			if (admin!=null) {
				// ServletActionContext.getResponse().getWriter().write("{\"status\":\"1\"}");
				this.getResponse().getWriter().write("{\"status\":\"1\"}");
				return;
			} else {
				this.getResponse().getWriter().write("{\"status\":\"0\"}");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
