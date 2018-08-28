package cn.hlyc.action.investment;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.action.filter.GetHttpResponseHeader;
import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.domain.userAccount.UserAccountModel;
import cn.hlyc.service.userAccount.IUserAccountService;
import cn.hlyc.utils.Response;

@Namespace("/investment")
@Controller
@Scope("prototype")
public class InvestmentAction extends BaseAction {

	@Autowired
	private BaseCacheService baseCacheService;

	@Autowired
	private IUserAccountService userAccountService;

	// 判断帐户余额是否可以进行投资
	@Action("checkAccount")
	public void checkAccount() {
		// 1.得到请求参数，投资的金额
		double account = Double.parseDouble(this.getRequest().getParameter("account"));
		// 2.得到帐户的信息
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		Map<String, Object> hmap = baseCacheService.getHmap(token);
		try {
			if (hmap == null || hmap.size() == 0) {
				this.getResponse().getWriter().write(Response.build().setStatus("15").toJSON());
				return;
			}
			int userId = (int) hmap.get("id");
			UserAccountModel uam = userAccountService.findByUserId(userId);
			// 3.得到帐户余额
			double balance = uam.getBalance();

			if (balance >= account) {
				// 可以购买
				this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
				return;
			} else {
				// 帐户余额不足
				this.getResponse().getWriter().write(Response.build().setStatus("17").toJSON());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
