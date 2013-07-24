package com.xinyuan.haze.system.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xinyuan.haze.core.entity.BaseEntity;

/**
 * 系统组织机构实体类
 * @author Sofar
 *
 */
@Entity
@Table(name="sec_group")
public class Group extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String name;
	
	/**
	 * 上级机构
	 */
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Group parent;
	
	/**
	 * 子机构
	 */
	@OneToMany(mappedBy="parent")
	private Set<Group> childs = new HashSet<Group>();
	
	@OneToMany(mappedBy="group")
	private Set<User> users = new HashSet<User>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	public Set<Group> getChilds() {
		return childs;
	}

	public void setChilds(Set<Group> childs) {
		this.childs = childs;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * 对组增加用户
	 * @param user 用户对象
	 */
	public void addUser(User user) {
		user.setGroup(this);
	}

	@Override
	public String toString() { 
		return "Group [id=" + id + ", name=" + name + "]";
	}
	
	@Transient
	private Long pid;

	public Long getPid() {
		return parent != null?parent.getId():null;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
}
