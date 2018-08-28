package cn.hlyc.action.jiaoYiJiLu;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.hlyc.action.common.BaseAction;
import cn.hlyc.action.filter.GetHttpResponseHeader;
import cn.hlyc.cache.BaseCacheService;
import cn.hlyc.domain.jylx.TransactionClass;
import cn.hlyc.domain.purchase.Purchase;
import cn.hlyc.domain.vo.JiaoYiJiLuVo;
import cn.hlyc.domain.vo.PurchaseVo;
import cn.hlyc.service.charges.IChargesService;
import cn.hlyc.service.jylx.IJiaoYiLeiXService;
import cn.hlyc.service.user.IUserService;
import cn.hlyc.utils.FrontStatusConstants;
import cn.hlyc.utils.Response;

@Namespace("/jiaoYiJiLu")
@Controller
@Scope("prototype")
public class JiaoYiJiLuAction extends BaseAction {
	@Autowired
	private BaseCacheService baseCacheService;

	@Autowired
	private IJiaoYiLeiXService iJiaoYiLeiXService;

	@Autowired
	private IChargesService chargesService;

	@Action("transaction_class")
	public void findClass() {

		try {

			this.getResponse().setCharacterEncoding("utf-8");
			List<TransactionClass> jylx = iJiaoYiLeiXService.findAll();

			if (!jylx.isEmpty()) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(jylx).toJSON());
			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Action("findPuerchase")
	public void findPuerchase() {

		try {

			this.getResponse().setCharacterEncoding("utf-8");
			List<JiaoYiJiLuVo> PurchaseAll = chargesService.findAll();

			if (!PurchaseAll.isEmpty()) {
				baseCacheService.setList("jy", PurchaseAll);
				baseCacheService.set("jysize", PurchaseAll.size() + "");
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(PurchaseAll).toJSON());
			} else {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Action("chaxun")
	public void chaxun() {

		try {
			HttpServletRequest request = getRequest();
			this.getResponse().setCharacterEncoding("utf-8");
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String username = request.getParameter("username2");
			String datetimeStart = request.getParameter("datetimeStart2");
			String datetimeEnd = request.getParameter("datetimeEnd2");
			List<JiaoYiJiLuVo> PurchaseAll = baseCacheService.lRange("jy", 0L,
					Long.parseLong(baseCacheService.get("jysize")));
			List<JiaoYiJiLuVo> PurchaseAll2 = new CopyOnWriteArrayList<JiaoYiJiLuVo>();
			if (username != null) {
				for (JiaoYiJiLuVo jiaoYiJiLu : PurchaseAll) {
					if (jiaoYiJiLu.getUsername().equals(username)) {
						PurchaseAll2.add(jiaoYiJiLu);
					}
				}
			} else {
				PurchaseAll2.addAll(PurchaseAll);
			}

			if (datetimeStart != null) {

				Date datetimeStart2 = sDateFormat.parse(datetimeStart);
				for (JiaoYiJiLuVo jiaoYiJiLu : PurchaseAll2) {
					if (jiaoYiJiLu.getBuyTime().before(datetimeStart2)) {
						PurchaseAll2.remove(jiaoYiJiLu);
					}
				}
			}

			if (datetimeEnd != null) {

				Date datetimeEnd2 = sDateFormat.parse(datetimeEnd);
				for (JiaoYiJiLuVo jiaoYiJiLu : PurchaseAll2) {
					if (jiaoYiJiLu.getBuyTime().after(datetimeEnd2)) {
						PurchaseAll2.remove(jiaoYiJiLu);
					}
				}
			}

			this.getResponse().getWriter()
					.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(PurchaseAll2).toJSON());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Action("chaxunTongJi1")
	public void chaxunTongJi1() {

		try {

			this.getResponse().setCharacterEncoding("utf-8");
			Double count = 0D;

			List<PurchaseVo> PurchaseList = chargesService.chaxunGroupByPname();
			
			if(PurchaseList ==null || PurchaseList.isEmpty()){
				this.getResponse().getWriter()
				.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
			
			for (PurchaseVo purchaseVo : PurchaseList) {
				count += purchaseVo.getZmoney();
			}

			for (PurchaseVo purchaseVo : PurchaseList) {

				Double pCount = (double) Math.round(purchaseVo.getZmoney() / count * 100) / 100;
				purchaseVo.setCount(pCount);
			}

			this.getResponse().getWriter()
					.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(PurchaseList).toJSON());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Action("chaxunTongJi2")
	public void chaxunTongJi2() {

		try {
			HttpServletRequest request = getRequest();
			this.getResponse().setCharacterEncoding("utf-8");
			Double count = 0D;
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String datetimeStartParam = request.getParameter("datetimeStartParam");
			String datetimeEndParam = request.getParameter("datetimeEndParam");
			String radioParam = request.getParameter("radioParam");
			List<PurchaseVo> PurchaseList = null;
			if (radioParam.equals("1")) {
				PurchaseList = chargesService.betweenRiQi(sDateFormat.parse(datetimeStartParam),
						sDateFormat.parse(datetimeEndParam));
			} else if (radioParam.equals("2")) {
				PurchaseList = chargesService.countProduct();
			}
			
			if(PurchaseList ==null || PurchaseList.isEmpty()){
				this.getResponse().getWriter()
				.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}

			
				for (PurchaseVo purchaseVo : PurchaseList) {
					count += purchaseVo.getZmoney();
				}

				for (PurchaseVo purchaseVo : PurchaseList) {

					Double pCount = (double) Math.round(purchaseVo.getZmoney() / count * 100) / 100;
					purchaseVo.setCount(pCount);
				}
	
		

			this.getResponse().getWriter()
					.write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setData(PurchaseList).toJSON());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
