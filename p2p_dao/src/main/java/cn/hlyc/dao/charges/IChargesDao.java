package cn.hlyc.dao.charges;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.purchase.Purchase;
import cn.hlyc.domain.vo.PurchaseVo;

public interface IChargesDao extends JpaRepository<Purchase, Integer> {

	@Query("select new cn.hlyc.domain.vo.PurchaseVo(p.pName,SUM(p.pAmount) ) from Purchase p group by p.pName")
	List<PurchaseVo> chaxunGroupByPname();

	@Query("select new cn.hlyc.domain.vo.PurchaseVo(p.pName,count(p) ) from Purchase p group by p.pName")
	List<PurchaseVo> countProduct();

	@Query("select new cn.hlyc.domain.vo.PurchaseVo(p.pName,sum(p.pAmount) ) from Purchase p where p.buyTime between ?1 and ?2 group by p.pName")
	List<PurchaseVo> betweenRiQi(Date parse, Date parse2);

}
