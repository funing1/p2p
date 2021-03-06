package cn.hlyc.action.bankCardInfo;

import java.io.IOException;
import java.util.List;
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
import cn.hlyc.domain.bankCardInfo.Bank;
import cn.hlyc.domain.bankCardInfo.BankCardInfo;
import cn.hlyc.domain.bankCardInfo.BankDictionary;
import cn.hlyc.domain.city.City;
import cn.hlyc.domain.mycity.Area;
import cn.hlyc.domain.mycity.Fcity;
import cn.hlyc.domain.mycity.Province;
import cn.hlyc.domain.user.UserModel;
import cn.hlyc.service.bank.IBankService;
import cn.hlyc.service.bankCardInfo.IBankCardInfoService;
import cn.hlyc.service.bankDictionary.IBankDictionaryService;
import cn.hlyc.service.city.ICityService;
import cn.hlyc.service.mycity.IfCityService;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.Response;
import cn.hlyc.utils.TokenUtil;

@Namespace("/bankCardInfo")
@Controller
@Scope("prototype")
public class BankCardInfoAction extends BaseAction implements ModelDriven<BankCardInfo> {
	@Autowired
	private BaseCacheService baseCacheService;

	@Autowired
	private IBankCardInfoService bankCardService;

	@Autowired
	private IBankService bankService;

	@Autowired
	private ICityService cityService;

	@Autowired
	private IBankDictionaryService bankDictionaryService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IfCityService ifCityService;

	private BankCardInfo bci = new BankCardInfo();

	@Override
	public BankCardInfo getModel() {

		return bci;
	}

	// 绑定银行卡
	@Action("addBankCardInfo")
	public void addBankCardInfo() {
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			if (StringUtils.isEmpty(token)) {
				// 没有token

				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());

				return;
			}
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			if (hmap == null || hmap.size() == 0) {

				// 没有登录
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;
			}
			// 1.得到请求参数--使用模型驱动
			// 2.查询用户是否绑定银行卡
			int userId = (int) baseCacheService.getHmap(token).get("id");
			BankCardInfo bankCardInfo = bankCardService.findByUserId(userId);

			if (bankCardInfo == null) {
				// 可以绑定
				bci.setUserId(userId);// 手动绑定用户的id
				bci.setCityId(Integer.parseInt(bci.getCountryNum()));
				bankCardService.addBankCardInfo(bci);
				this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
				return;
			} else {
				// 已经绑定
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.CARDINFO_ALREADY_EXIST).toJSON());
				return;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// 获取级联数据
	@Action("findCity")
	public void findCity() {
		this.getResponse().setCharacterEncoding("utf-8");
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			if (StringUtils.isEmpty(token)) {
				// 没有token

				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());

				return;
			}
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			if (hmap == null || hmap.size() == 0) {

				// 没有登录
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;
			}
			// 1.得到请求参数
			String cityAreaNum = this.getRequest().getParameter("cityAreaNum");
		
			// 2.调用CityService查询数据
			List<Fcity> cs = ifCityService.findByParentCity(cityAreaNum);
		
			if (!cs.isEmpty()) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(cs).toJSON());
				return;
			} else {
				
				List<Area> cs2 = ifCityService.findByParentCity2(cityAreaNum);
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(cs2).toJSON());

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Action("findUserInfo")
	public void findUserInfo() {
		this.getResponse().setCharacterEncoding("utf-8");
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			if (StringUtils.isEmpty(token)) {
				// 没有token
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());
				return;
			}
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			if (hmap == null || hmap.size() == 0) {

				// 没有登录
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;
			}
			String _username = TokenUtil.getTokenUserName(token); // 从token中获取的用户名
			// 1. 得到请求参数username
			String username = this.getRequest().getParameter("username");// 从浏览器端传递过来的用户名
			// 2. 验证用户名正确，通过token从redis中获取用户id
			Integer userId = null;
			if (_username.startsWith(username)) {
				userId = (Integer) hmap.get("id");
			}
			// 3.根据userId查询用户信息
			UserModel userModel = userService.findById(userId);

			this.getResponse().getWriter().write(Response.build().setStatus("1").setData(userModel).toJSON());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询所有省份
	@Action("findProvince")
	public void findProvince() {
		this.getResponse().setCharacterEncoding("utf-8");
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			if (StringUtils.isEmpty(token)) {
				// 没有token

				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());

				return;
			}
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			if (hmap == null || hmap.size() == 0) {

				// 没有登录
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;
			}
			// 1.调用cityService完成查询所有省份操作
			List<Province> cs = ifCityService.findProvince();

			// 2.响应

			this.getResponse().getWriter()
					.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(cs).toJSON());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询所有银行信息
	@Action("findAllBanks")
	public void findAllBanks() {
		this.getResponse().setCharacterEncoding("utf-8");
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			if (StringUtils.isEmpty(token)) {
				// 没有token
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());
				return;
			}
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			if (hmap == null || hmap.size() == 0) {

				// 没有登录
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;
			}
			// 1.调用bankService来查询所有银行信息
			// List<Bank> banks = bankService.findAll();
			// List<Bank> banks = bankService.findDistinctBank();
			List<BankDictionary> banks = bankDictionaryService.findAll();

			this.getResponse().getWriter()
					.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(banks).toJSON());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询用户的银行卡信息
	@Action("findBankInfoByUsername")
	public void findBankInfoByUsername() {
		this.getResponse().setCharacterEncoding("utf-8");
		String token = GetHttpResponseHeader.getHeadersInfo(this.getRequest());
		try {
			if (StringUtils.isEmpty(token)) {
				// 没有token
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NULL_TOKEN).toJSON());
				return;
			}
			Map<String, Object> hmap = baseCacheService.getHmap(token);
			if (hmap == null || hmap.size() == 0) {

				// 没有登录
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
				return;
			}
			String _username = TokenUtil.getTokenUserName(token); // 从token中获取的用户名
			// 1. 得到请求参数username
			String username = this.getRequest().getParameter("username");// 从浏览器端传递过来的用户名
			// 2. 验证用户名正确，通过token从redis中获取用户id
			Integer userId = null;
			if (_username.startsWith(username)) {
				userId = (Integer) hmap.get("id");
			}
			// 3. 根据用户的id查询银行卡信息
			if (userId != null) {
				BankCardInfo bci = bankCardService.findByUserId(userId);
				// 4. 判断是否查询到结果，返回信息到浏览器
				if (bci != null) {
					this.getResponse().getWriter()
							.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(bci).toJSON());
					return;
				} else {
					this.getResponse().getWriter()
							.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
					return;
				}
			}
			this.getResponse().getWriter()
					.write(Response.build().setStatus(FrontStatusConstants.NOT_LOGGED_IN).toJSON());
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
