package cn.hlyc.service.city.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.city.ICityDAO;
import cn.hlyc.domain.city.City;
import cn.hlyc.service.city.ICityService;

@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	private ICityDAO cityDao;

	@Override
	public List<City> findProvince() {
		return cityDao.findByParentCityAreaNumIsNull();
	}

	@Override
	public List<City> findByParentCityAreaNum(String parentId) {
		return cityDao.findByParentCityAreaNum(parentId);
	}
}
