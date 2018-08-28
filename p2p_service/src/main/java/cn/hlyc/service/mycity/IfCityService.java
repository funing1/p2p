package cn.hlyc.service.mycity;

import java.util.List;

import cn.hlyc.domain.city.City;
import cn.hlyc.domain.mycity.Area;
import cn.hlyc.domain.mycity.Fcity;
import cn.hlyc.domain.mycity.Province;

public interface IfCityService {
	List<Province> findProvince();

	List<Fcity> findByParentCity(String cityAreaNum);

	List<Area> findByParentCity2(String cityAreaNum);

}
