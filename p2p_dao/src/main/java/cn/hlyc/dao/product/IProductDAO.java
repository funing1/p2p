package cn.hlyc.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.product.Product;

public interface IProductDAO extends JpaRepository<Product, Long>{

	@Modifying
	@Query("delete from Product p where p.proId=?1")
	void delById(Long parseInt);

	@Query("select p from Product p where p.productName=?1")
	Product findByProductName(String getpName);

}
