package cn.hlyc.action.jiekuan;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.action.filter.GetHttpResponseHeader;
import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.domain.jiekuan.UserLoan;
import cn.hlyc.domain.user.UserModel;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.service.userAccount.IUserAccountService;
import cn.hlyc.service.userLoan.IUserLoanService;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.Response;

@Namespace("/userLoan")
@Controller
@Scope("prototype")
public class UserLoanAction extends BaseAction implements ModelDriven<UserModel> {
	private UserModel user = new UserModel();
	@Autowired // 默认by type
	// @Resource(name="redisCache") // by name
	private BaseCacheService baseCacheService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserAccountService userAccountService;
	
	@Autowired
	private IUserLoanService userLoanService;

	@Override
	public UserModel getModel() {

		return user;
	}

	// 提交借款申请操作
	@Action("saveUserLoan")
	public void saveUserLoan() {
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
			Integer userId = (int) hmap.get("id");
			String loanUsername = this.getRequest().getParameter("loanUsername");
			Integer sex =  Integer.parseInt(this.getRequest().getParameter("sex"));
			double loanMoney =  Double.parseDouble(this.getRequest().getParameter("loanMoney"));
			String phone =  this.getRequest().getParameter("phone");
			Integer address =  Integer.parseInt(this.getRequest().getParameter("address"));
			
			UserLoan userLoan = new UserLoan(loanUsername,sex,loanMoney,phone,address,userId,new Date());
			
			// 操作代码
			 userAccountService.updateByUserId(userId,loanMoney);
			 
			 userLoanService.save(userLoan);
			 
			// 3.响应状态码1

			this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
