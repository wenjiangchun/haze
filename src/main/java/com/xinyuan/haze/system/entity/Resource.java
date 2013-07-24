package com.xinyuan.haze.system.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.xinyuan.haze.core.entity.BaseEntity;
import com.xinyuan.haze.system.utils.ResourceType;



/**
 * 系统资源权限实体类
 * @author Sofar
 *
 */
@Entity
@Table(name="sec_resource")
public class Resource extends BaseEntity<Long> { 
	
	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String name;
	
	private String permission;

	private String url;
	
	@Enumerated(EnumType.STRING)
	private ResourceType resourceType;
	
	/**
	 * 上级资源
	 */
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Resource parent;
	
	/**
	 * 子机构
	 */
	@OneToMany(mappedBy="parent")
	private Set<Resource> childrens;
	
	@ManyToMany(mappedBy="resources")
	private Set<Role> roles;

	public Resource() {
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public Set<Resource> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Resource> childrens) {
		this.childrens = childrens;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void removeAllChildrens() {
		for (Resource resource : this.childrens) {
			resource.setParent(null);
		}
	}

	@Override
	public String toString() {
		String url = this.url != null ? this.url : "";
		return "Resource [permission=" + permission + ",name=" + name + ",resourceType=" + resourceType.getTypeName() + ",url=" + url + "]";
	}
}