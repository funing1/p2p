package cn.hlyc.service.productRate;

import java.util.List;

import cn.hlyc.domain.product.ProductEarningRate;

public interface IProductRateService {

	List<ProductEarningRate> findByProId(String pid);

}
