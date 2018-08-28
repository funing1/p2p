package cn.hlyc.action.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.domain.user.UserModel;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.MD5Util;
import cn.hlyc.utils.Response;

@Namespace("/updateUser")
@Controller
@Scope("prototype")
public class UpdateUserAction extends BaseAction {
	
	@Autowired
	private IUserService userService;

	@Action("update")
	public void update() {
		String id = this.getRequest().getParameter("id");
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		String registerTime = this.getRequest().getParameter("registerTime");
		String phone = this.getRequest().getParameter("phone");
		String email = this.getRequest().getParameter("email");
		String phoneStatus = this.getRequest().getParameter("phoneStatus");
		String emailStatus = this.getRequest().getParameter("emailStatus");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		UserModel us = new UserModel();
		us.setId(Integer.parseInt(id));
		us.setUsername(username);		
		us.setPassword(password);
		String pwdMd5 = MD5Util.md5(us.getUsername() + us.getPassword().toLowerCase());// 将其进行md5加密
		us.setPassword(pwdMd5);
		try {
			us.setRegisterTime(sdf.parse(registerTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		us.setPhone(phone);
		us.setEmail(email);
		us.setPhoneStatus(Integer.parseInt( phoneStatus));
		us.setEmailStatus(Integer.parseInt(emailStatus));
		
		userService.update(us);
		
		try {
			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
