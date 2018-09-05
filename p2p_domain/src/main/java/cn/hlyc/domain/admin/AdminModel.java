package cn.hlyc.domain.admin;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.hlyc.domain.privilege.Privilege;
import cn.hlyc.domain.role.Role;

@Entity
@Table(name = "t_admin")
public class AdminModel {

	@Id
	@GeneratedValue // 这个是主键生成策略,当前类似于hibernate中的native
	private Integer id;
	@Column(name = "t_username", length = 20)
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Transient
	private List<Role> rolelist;

	@Transient
	private List<Privilege> privilegelist;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	public List<Privilege> getPrivilegelist() {
		return privilegelist;
	}

	public void setPrivilegelist(List<Privilege> privilegelist) {
		this.privilegelist = privilegelist;
	}

	@Override
	public String toString() {
		return "AdminModel [id=" + id + ", username=" + username + ", password=" + password + ", rolelist=" + rolelist
				+ ", privilegelist=" + privilegelist + "]";
	}

}
