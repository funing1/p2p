package cn.hlyc.domain.adminRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_ADMIN")
public class Admin_role {

	@Id
	@GeneratedValue // 这个是主键生成策略,当前类似于hibernate中的native
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ADMIN_ID")
	private Integer adminId;

	@Column(name = "ROLE_ID")
	private Integer roleId;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Admin_role [id=" + id + ", adminId=" + adminId + ", roleId=" + roleId + "]";
	}

	public Admin_role(Integer adminId, Integer roleId) {
		super();
		this.adminId = adminId;
		this.roleId = roleId;
	}

	public Admin_role() {
		super();
		// TODO Auto-generated constructor stub
	}

}
