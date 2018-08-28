package cn.hlyc.action.charges;

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
import cn.hlyc.domain.jiekuan.UserLoan;
import cn.hlyc.domain.purchase.Purchase;
import cn.hlyc.service.charges.IChargesService;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.service.userAccount.IUserAccountService;
import cn.hlyc.service.userLoan.IUserLoanService;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.Response;

@Namespace("/charges")
@Controller
@Scope("prototype")
public class ChargesAction extends BaseAction {

	@Autowired
	private IChargesService chargesService;

	@Autowired
	private BaseCacheService baseCacheService;

	@Autowired
	private IUserAccountService userAccountService;

	@Action("addMayTake")
	public void addMayTake() {
		// 1.得到token
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			// 判断token不为空
			if (StringUtils.isEmpty(token)) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());
				return;
			}
			// 2.从redis中根据token获取信息，可以获取到用户id
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			// 判断hmap不为空
			if (hmap == null || hmap.size() == 0) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;
			}
			// 3.当前用户id
			Integer userId = (int) hmap.get("id");
			// 要购买的产品的名字
			String pName = this.getRequest().getParameter("pName");
			// 购买选择的方案
			String pProductId = this.getRequest().getParameter("pProductId");
			// 购买选择的本金
			String pAmount = this.getRequest().getParameter("pAmount");
			// 购买选择的投资期限
			String pDeadline = this.getRequest().getParameter("pDeadline");
			// 利润
			String pExpectedAnnualIncome = this.getRequest().getParameter("pExpectedAnnualIncome");
			// 预期收益
			String pMonthInterest = this.getRequest().getParameter("pMonthInterest");
			// 每月提取利息
			String pMonthlyExtractInterest = this.getRequest().getParameter("pMonthlyExtractInterest");

			chargesService.add(userId, pName, Integer.parseInt(pProductId), Integer.parseInt(pAmount),
					Integer.parseInt(pDeadline), Double.parseDouble(pExpectedAnnualIncome),
					Double.parseDouble(pMonthInterest), Double.parseDouble(pMonthlyExtractInterest));
           
			userAccountService.updateSubtraction(userId, Double.parseDouble(pAmount));

			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
