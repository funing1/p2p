package cn.hlyc.service.product.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hlyc.dao.product.IProductDAO;
import cn.hlyc.dao.product.IProductRateDAO;
import cn.hlyc.domain.product.Product;
import cn.hlyc.domain.product.ProductEarningRate;
import cn.hlyc.service.product.IProductService;
import cn.hlyc.utils.ProductStyle;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDAO productDao;

	@Autowired
	private IProductRateDAO productRateDao;

	// 根据理财产品id查询理财产品的利率信息
	@Override
	public List<ProductEarningRate> findRateByProId(String proId) {

		return productRateDao.findByProductId(Integer.parseInt(proId));
	}

	// 根据id查找
	public Product findById(Long proId) {

		Product p = productDao.findOne(proId);
		// 处理数据
		changeStatusToChinese(p);
		return p;
	}

	// 查找所有
	@Override
	public List<Product> findAll() {

		List<Product> ps = productDao.findAll();
		// 将数据处理
		changeStatusToChinese(ps);

		return ps;
	}

	private void changeStatusToChinese(Product products) {
		List<Product> ps = new ArrayList<Product>();
		ps.add(products);
		changeStatusToChinese(ps);
	}

	/**
	 * 方法描述：将状态转换为中文
	 * 
	 * @param products
	 *            void
	 */
	private void changeStatusToChinese(List<Product> products) {
		if (null == products)
			return;
		for (Product product : products) {
			int way = product.getWayToReturnMoney();
			// 每月部分回款
			if (ProductStyle.REPAYMENT_WAY_MONTH_PART.equals(String.valueOf(way))) {
				product.setWayToReturnMoneyDesc("每月部分回款");
				// 到期一次性回款
			} else if (ProductStyle.REPAYMENT_WAY_ONECE_DUE_DATE.equals(String.valueOf(way))) {
				product.setWayToReturnMoneyDesc("到期一次性回款");
			}

			// 是否复投 isReaptInvest 136：是、137：否
			// 可以复投
			if (ProductStyle.CAN_REPEAR == product.getIsRepeatInvest()) {
				product.setIsRepeatInvestDesc("是");
				// 不可复投
			} else if (ProductStyle.CAN_NOT_REPEAR == product.getIsRepeatInvest()) {
				product.setIsRepeatInvestDesc("否");
			}
			// 年利率
			if (ProductStyle.ANNUAL_RATE == product.getEarningType()) {
				product.setEarningTypeDesc("年利率");
				// 月利率 135
			} else if (ProductStyle.MONTHLY_RATE == product.getEarningType()) {
				product.setEarningTypeDesc("月利率");
			}

			if (ProductStyle.NORMAL == product.getStatus()) {
				product.setStatusDesc("正常");
			} else if (ProductStyle.STOP_USE == product.getStatus()) {
				product.setStatusDesc("停用");
			}

			// 是否可转让
			if (ProductStyle.CAN_NOT_TRNASATION == product.getIsAllowTransfer()) {
				product.setIsAllowTransferDesc("否");
			} else if (ProductStyle.CAN_TRNASATION == product.getIsAllowTransfer()) {
				product.setIsAllowTransferDesc("是");
			}
		}
	}

	// 修改操作
	
	@Override
	public void update(Product product) {
		// 1.修改利率操作，是先删除，在添加
		List<ProductEarningRate> pers = productRateDao.findByProductId((int) product.getProId());// 已经有的利率
		// 删除
		if (pers != null && pers.size() > 0) {
			productRateDao.delByProId((int) product.getProId());
		}
		// 添加
		productRateDao.save(product.getProEarningRate());
		// 2.修改产品信息
		productDao.save(product);
	}

	@Override
	public void add(Product product) {
		Product saveProduct = productDao.save(product);
		saveProduct.getProEarningRate().get(0).setProductId((int) saveProduct.getProId());
		productRateDao.save(saveProduct.getProEarningRate());
	}


	@Override
	public void delById(int parseInt) {
		productDao.delById((long) parseInt);
		productRateDao.delByProId(parseInt);
	}

	

}
