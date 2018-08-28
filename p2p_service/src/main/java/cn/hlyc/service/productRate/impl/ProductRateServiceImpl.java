package cn.hlyc.service.productRate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.product.IProductRateDAO;
import cn.hlyc.domain.product.ProductEarningRate;
import cn.hlyc.service.productRate.IProductRateService;

@Service
public class ProductRateServiceImpl implements IProductRateService{

	@Autowired
	private IProductRateDAO productRateDao;

	@Override
	public List<ProductEarningRate> findByProId(String pid) {
		return productRateDao.findByProductId(Integer.parseInt(pid));
	}
}
