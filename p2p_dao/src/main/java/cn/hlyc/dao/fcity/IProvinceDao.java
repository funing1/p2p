package cn.hlyc.dao.fcity;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hlyc.domain.city.City;
import cn.hlyc.domain.mycity.Province;

public interface IProvinceDao extends JpaRepository<Province, Integer>{

}
