package com.xinyuan.haze.system.entity;

import java.util.HashSet; 
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.xinyuan.haze.core.entity.BaseEntity;
import com.xinyuan.haze.system.utils.Sex;
import com.xinyuan.haze.system.utils.Status;

/**
 * 系统用户信息实体类
 * @author sofar
 *
 */
@Entity
@Table(name="sec_user")
public class User extends BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	public static final String ADMIN = "admin"; //超级管理员用户登录名

	/**
	 * 登录名
	 */
	@Column(unique=true)
	private String loginName;

	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;

	private String email;
	
	/**
	 * 用户状态
	 */
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String salt;
	
	/**
	 * 用户角色组
	 */
	@ManyToMany
	@JoinTable(
		name="sec_user_role"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private Set<Role> roles = new HashSet<Role>();
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;
	
	public User() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * 对用户增加角色
	 * @param role 角色对象
	 * @return 当前用户所有角色
	 */
	public Set<Role> addRole(Role role) {
	   this.roles.add(role);
	   return this.roles;
	}
	
	/**
	 * 删除用户角色
	 * @param role 角色对象
	 * @return 当前用户所有角色
	 */
	public Set<Role> removeRole(Role role) {
		this.roles.remove(role);
		return this.roles;
	}
	
	/**
	 * 获取用户所有资源权限，包括用户角色所有权限
	 * @return 用户当前资源权限
	 */
	public Set<String> getAllPermission() {
		Set<String> permissions = new HashSet<String>();
		for(Role role : this.getRoles()) {
			permissions.addAll(role.getAllPermissons());
		}
		return permissions;
	}

	/**
	 * 获取用户所有角色名称 以逗号隔开
	 * @return 角色名称
	 */
	public String getRoleNames() {
		String roleName = "";
		if (roles != null) {
			for (Role role : roles) {
				roleName += role.getName() + ",";
			}
		}
		return roleName;
	}
	/**
	 * 判断用户是否为超级管理员 如果loginName为"admin"则为超级管理员
	 * @return true/false
	 */
	public boolean isSuperAdmin() {
		return this.loginName.equals(ADMIN);
	}

	@Override
	public String toString() {
		String email = this.email != null ? this.email : "";
		return "User [loginName=" + loginName + ",name=" + name + ",sex=" + sex.getSexName()+",email=" + email + ",status=" + status.getStatusName() + "]";
	}

}