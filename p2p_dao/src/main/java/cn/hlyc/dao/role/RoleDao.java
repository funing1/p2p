package cn.hlyc.dao.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.hlyc.domain.role.Role;

public interface RoleDao extends JpaRepository<Role, Integer>{
	

}
