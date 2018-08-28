package cn.hlyc.service.product;

import java.util.List;

import cn.hlyc.domain.product.Product;
import cn.hlyc.domain.product.ProductEarningRate;

public interface IProductService {

	List<Product> findAll();
	public Product findById(Long proId);
	List<ProductEarningRate> findRateByProId(String proId);
	void update(Product product);
	void add(Product product);
	void delById(int parseInt);

}
