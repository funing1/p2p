package cn.hlyc.domain.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.hlyc.domain.privilege.Privilege;

@Entity
@Table(name = "ROLE")
public class Role {
	@Id
	@GeneratedValue()
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ROLE_NAME")
	private String rolename;

	@Column(name = "ISPARENT")
	private Integer isparent;

	@Column(name = "PARENT_ID")
	private Integer parentId;

	@Transient
	private List<Role> rolesChildrens = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getIsparent() {
		return isparent;
	}

	public void setIsparent(Integer isparent) {
		this.isparent = isparent;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Role> getRolesChildrens() {
		return rolesChildrens;
	}

	public void setRolesChildrens(List<Role> rolesChildrens) {
		this.rolesChildrens = rolesChildrens;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", isparent=" + isparent + ", parentId=" + parentId
				+ ", rolesChildrens=" + rolesChildrens + "]";
	}

}
