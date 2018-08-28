package cn.hlyc.service.jylx.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hlyc.dao.JiaoYiLeiX.IJiaoYiLeiXDao;
import cn.hlyc.domain.jylx.TransactionClass;
import cn.hlyc.service.jylx.IJiaoYiLeiXService;

@Service
public class JiaoYiLeiXServiceImpl implements IJiaoYiLeiXService {
	
	@Autowired
	private IJiaoYiLeiXDao iJiaoYiLeiXDao;

	@Override
	public List<TransactionClass> findAll() {
		// TODO Auto-generated method stub
		return iJiaoYiLeiXDao.findAll();
	}

}
