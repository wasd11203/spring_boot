package com.telincn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 与 数据库中Demo表 对应的实体类 1. 符合java bean 规范: 1. 必须添加默认的空的构造方法 2. 注解都是 javax 包下的 3.
 * 注解@Id必须存在,写在实体中对应表主键的属性上,
 * 
 * @GeneratedValue:声明主键的生成策略 4. @Column注解用于将表中的字段 映射到对应的属性上 注意:
 *                           在数据库中的表的字段名,一定不要存在大写字母
 * @author 99
 *
 */
@Entity
@Table(name = "demo")
public class ExEntity implements Serializable {

	private static final long serialVersionUID = 2609346196470291892L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	public ExEntity() {
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExEntity other = (ExEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExEntity [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
