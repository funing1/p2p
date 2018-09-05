package cn.hlyc.domain.privilege;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PRIVILEGE")
public class Privilege {
	@Id
	@GeneratedValue()
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ISPARENT")
	private Integer isparent;

	@Column(name = "PARENT_ID")
	private Integer parentId;

	@Column(name = "TYPE")
	private Integer type;

	@Column(name = "LI_NGCLASS")
	private String liNgClass;

	@Column(name = "IMGSRC")
	private String imgsrc;

	@Column(name = "UL_STYLE")
	private String ulStyle;

	@Column(name = "LI_NGIF")
	private String liNgif;

	@Column(name = "LI_UISREF")
	private String liuisref;

	@Transient
	private List<Privilege> privilegeChildren = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLiNgClass() {
		return liNgClass;
	}

	public void setLiNgClass(String liNgClass) {
		this.liNgClass = liNgClass;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public String getUlStyle() {
		return ulStyle;
	}

	public void setUlStyle(String ulStyle) {
		this.ulStyle = ulStyle;
	}

	public String getLiNgif() {
		return liNgif;
	}

	public void setLiNgif(String liNgif) {
		this.liNgif = liNgif;
	}

	public String getLiuisref() {
		return liuisref;
	}

	public void setLiuisref(String liuisref) {
		this.liuisref = liuisref;
	}

	public List<Privilege> getPrivilegeChildren() {
		return privilegeChildren;
	}

	public void setPrivilegeChildren(List<Privilege> privilegeChildren) {
		this.privilegeChildren = privilegeChildren;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + ", isparent=" + isparent + ", parentId=" + parentId
				+ ", type=" + type + ", liNgClass=" + liNgClass + ", imgsrc=" + imgsrc + ", ulStyle=" + ulStyle
				+ ", liNgif=" + liNgif + ", liuisref=" + liuisref + ", privilegeChildren=" + privilegeChildren + "]";
	}

}
