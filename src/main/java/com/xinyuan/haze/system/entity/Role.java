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
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.xinyuan.haze.core.entity.BaseEntity;
import com.xinyuan.haze.system.utils.Status;

/**
 * 系统角色实体类
 * @author Sofar
 *
 */
@Entity
@Table(name="sec_role")
public class Role extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String roleName;
	
	private String name;
	
	/**
	 * 角色状态
	 */
	@Enumerated(EnumType.STRING)
	private Status status;
	
	/**
	 * 角色资源
	 */
	@ManyToMany
	@JoinTable(
			name="sec_role_resource"
			, joinColumns={
				@JoinColumn(name="role_id")
				}
			, inverseJoinColumns={
				@JoinColumn(name="resource_id")
				}
			)
	private Set<Resource> resources = new HashSet<Resource>();

	/**
	 * 角色用户
	 */
	@ManyToMany(mappedBy="roles")
	private Set<User> users;

	public Role() {
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * 对角色增加资源权限
	 * @param resource 资源对象
	 * @return 当前角色所有资源权限
	 */
	public Set<Resource> addResource(Resource resource) {
		this.resources.add(resource);
		return this.resources;
	}
	
	/**
	 * 删除角色资源权限
	 * @param resource 资源对象
	 * @return 当前角色所有资源权限
	 */
	public Set<Resource> removeResource(Resource resource) {
		this.resources.remove(resource);
		return this.resources;
	}

	public Set<String> getAllPermissons() {
		Set<String> permissions = new HashSet<String>();
		Set<Resource> resources = this.getResources();
		for (Resource resource : resources) {
			if (StringUtils.isNotEmpty(resource.getPermission())) {
				permissions.add(resource.getPermission());
			}
		}
		return permissions;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", roleName=" + roleName + ", status=" + status.getStatusName()
				+ "]";
	}
	
}