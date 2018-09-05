package cn.hlyc.domain.rolePrivilege;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_PRIVILEGE")
public class Role_Privilege {
	@Id
	@GeneratedValue // 这个是主键生成策略,当前类似于hibernate中的native
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ROLE_ID")
	private Integer roleid;

	@Column(name = "PRIVILEGE_ID")
	private Integer privilegeid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getPrivilegeid() {
		return privilegeid;
	}

	public void setPrivilegeid(Integer privilegeid) {
		this.privilegeid = privilegeid;
	}

	@Override
	public String toString() {
		return "Role_Privilege [id=" + id + ", roleid=" + roleid + ", privilegeid=" + privilegeid + "]";
	}

	public Role_Privilege() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role_Privilege(Integer roleid, Integer privilegeid) {
		super();
		this.roleid = roleid;
		this.privilegeid = privilegeid;
	}

}
