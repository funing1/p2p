package cn.hlyc.dao.fcity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.mycity.Fcity;
import cn.hlyc.domain.mycity.Province;

public interface IFCityDao extends JpaRepository<Fcity, Integer>{
	@Query("select fcity from Fcity fcity where fcity.parentCityAreaNum = ?1")
	List<Fcity> findByParentCity(String cityAreaNum);

}
