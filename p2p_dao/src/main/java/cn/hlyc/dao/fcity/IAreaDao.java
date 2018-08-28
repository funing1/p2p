package cn.hlyc.dao.fcity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.mycity.Area;
import cn.hlyc.domain.mycity.Province;

public interface IAreaDao extends JpaRepository<Area, Integer>{
	@Query("select area from Area area where area.parentCityAreaNum = ?1")
	List<Area> findByParentCity(String cityAreaNum);

}
