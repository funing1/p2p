package cn.hlyc.service.mycity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.fcity.IAreaDao;
import cn.hlyc.dao.fcity.IFCityDao;
import cn.hlyc.dao.fcity.IProvinceDao;
import cn.hlyc.domain.mycity.Area;
import cn.hlyc.domain.mycity.Fcity;
import cn.hlyc.domain.mycity.Province;
import cn.hlyc.service.mycity.IfCityService;

@Service
public class fCityServiceImpl implements IfCityService {
	@Autowired
	private IProvinceDao iProvinceDao;
	
	@Autowired
	private IFCityDao iFCityDao;
	
	@Autowired
	private IAreaDao iAreaDao;

	@Override
	public List<Province> findProvince() {
		// TODO Auto-generated method stub
		return iProvinceDao.findAll();
	}

	@Override
	public List<Fcity> findByParentCity(String cityAreaNum) {
		// TODO Auto-generated method stub
		return iFCityDao.findByParentCity(cityAreaNum);
	}

	@Override
	public List<Area> findByParentCity2(String cityAreaNum) {
		// TODO Auto-generated method stub
		return iAreaDao.findByParentCity(cityAreaNum);
	}

}
