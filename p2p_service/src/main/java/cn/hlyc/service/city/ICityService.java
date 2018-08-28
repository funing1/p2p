package cn.hlyc.service.city;

import java.util.List;

import cn.hlyc.domain.city.City;

public interface ICityService {

	List<City> findProvince();

	List<City> findByParentCityAreaNum(String string);

}
