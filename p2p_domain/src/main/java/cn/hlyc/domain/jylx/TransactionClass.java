package cn.hlyc.domain.jylx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION_CLASS")
public class TransactionClass {
	@Id
	@GeneratedValue()
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CLASSNUMBER")
	private Integer classnumber;

	@Column(name = "JYLX")
	private String jylx;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassnumber() {
		return classnumber;
	}

	public void setClassnumber(Integer classnumber) {
		this.classnumber = classnumber;
	}

	public String getJylx() {
		return jylx;
	}

	public void setJylx(String jylx) {
		this.jylx = jylx;
	}

	@Override
	public String toString() {
		return "TransactionClass [id=" + id + ", classnumber=" + classnumber + ", jylx=" + jylx + "]";
	}

}
