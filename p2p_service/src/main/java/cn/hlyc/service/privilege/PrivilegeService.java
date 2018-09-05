package cn.hlyc.service.privilege;

import java.util.List;

import cn.hlyc.domain.privilege.Privilege;

public interface PrivilegeService {

	List<Privilege> findAll();

	Privilege findById(Integer privilegeId);

}
