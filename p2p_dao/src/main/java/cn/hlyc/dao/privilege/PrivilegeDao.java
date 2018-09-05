package cn.hlyc.dao.privilege;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.hlyc.domain.privilege.Privilege;
import cn.hlyc.domain.role.Role;

public interface PrivilegeDao  extends JpaRepository<Privilege, Integer>{

}
