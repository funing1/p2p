package cn.hlyc.action.paymentpwd;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.action.filter.GetHttpResponseHeader;
import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.Response;

@Namespace("/paymentpwd")
@Controller
@Scope("prototype")
public class PaymentpwdAction extends BaseAction {

	@Autowired
	private BaseCacheService baseCacheService;

	@Autowired
	private IUserService userService;

	// 设置支付密码
	@Action("getPaymentPwd")
	public void getPaymentPwd() {
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			if (StringUtils.isEmpty(token)) {
				// token过期
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());
				return;
			}
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			if (hmap == null || hmap.size() == 0) {
				// 用户未登录
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;

			}

			String paypwd = this.getRequest().getParameter("paypwd");
			userService.updatePayPasswordAndPayPwdStatus(paypwd, (int) (hmap.get("id")));

			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
