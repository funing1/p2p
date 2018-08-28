package cn.hlyc.action.emailAuth;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.service.email.IEmailService;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.utils.EmailUtils;
import cn.hlyc.utils.Response;
import cn.hlyc.utils.SecretUtil;

@Namespace("/emailAuth")
@Controller
@Scope("prototype")
public class EmailAuthAction extends BaseAction {

	@Autowired
	private BaseCacheService baseCacheService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmailService emailService;

	// 发送验证邮件
	@Action("updateEmail")
	public void updateEmail() {
		try {
			// 1. 得到请求参数
			String userId = this.getRequest().getParameter("userId");
			String username = this.getRequest().getParameter("username");
			String email = this.getRequest().getParameter("email");
			String newEmail = this.getRequest().getParameter("newEmail");

			if (email.equals(newEmail)) {
				this.getResponse().getWriter().write(Response.build().setStatus("0").toJSON());
				return;
			}

			// 2. 调用service完成发送邮件操作
			String title = "P2P邮箱修改认证激活";

			String enc = SecretUtil.encrypt(userId);// 加密后的id
			String content = EmailUtils.getUpdateMailCapacity(newEmail, enc, username);
			emailService.sendEmail(newEmail, title, content); // 发送邮件
			userService.addEmail(newEmail, Integer.parseInt(userId));

			this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
