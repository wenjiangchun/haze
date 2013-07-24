package com.xinyuan.haze.system.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.xinyuan.haze.core.service.AbstractBaseService;
import com.xinyuan.haze.system.dao.RoleDao;
import com.xinyuan.haze.system.entity.Resource;
import com.xinyuan.haze.system.entity.Role;
import com.xinyuan.haze.system.entity.User;
import com.xinyuan.haze.system.exception.RoleExistException;
import com.xinyuan.haze.system.utils.Status;

/**
 * 角色对象Service
 * @author Sofar
 *
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends AbstractBaseService<Role, Long>{
	
	private RoleDao roleDao;
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		super.setDao(roleDao);
	}
	
	@Autowired(required=false)
	private IdentityService identityService;
	
	public List<Role> findByStatus(Status status) {
		return this.roleDao.findByStatus(status);
	}
	
	/**
	 * 根据角色状态获取系统中所有的角色名称
	 * @param status 角色状态
	 * @return 角色名称集合
	 */
	public List<String> findAllRoleNameByStatus(Status status) {
		List<String> roleNameList = new ArrayList<String>();
		List<Role> roleList = findByStatus(status);
		for (Role role : roleList) {
			roleNameList.add(role.getName());
		}
		return roleNameList;
	}
	/**
	 * 保存角色对象，保存前会校验角色名称是否存在，如果角色名称已存在则抛出RoleExistException
	 * @param role 角色对象
	 * @return 
	 * @throws RoleExistException 角色已存在对象
	 */
	@CacheEvict(value="shiroCache",allEntries=true)
	@Transactional(readOnly = false)
	public Role saveOrUpdate(Role role) throws RoleExistException {
		Assert.notNull(role.getName());
		Role ro = this.roleDao.findByRoleName(role.getName());
		if (null != ro && role.getId() == null) {
			logger.error("角色保存失败，角色名称{}已存在！" + role.getName());
			throw new RoleExistException("角色名称" + role.getName() + "已存在");
		} else {
			logger.info("保存角色成功，角色名为{}", role.getName());
			this.roleDao.save(role);
			
			//同步Activiti角色
			identityService.saveGroup(identityService.newGroup(role.getId().toString()));
			
		}
		return ro;
	}
	
	/**
	 * 删除所有角色，同时删除和角色关联的用户，组织，资源的关联信息
	 */

	@CacheEvict(value="shiroCache",allEntries=true)
	@Transactional(readOnly = false)
	public void deleteALl() {
		List<Role> roleList = this.findAll();
		for (Role role : roleList) {
			delete(role.getId());
		}
	}
	
	/**
	 * 删除角色 同时删除和该角色关联的用户 ， 资源的关联信息
	 * @param id 角色Id
	 */
	@CacheEvict(value="shiroCache",allEntries=true)
	@Transactional(readOnly = false)
	public void delete(Long id) {
		Role role = this.roleDao.findOne(id);
		if (role != null) {
			role.setUsers(null);
			/*Set<User> users = role.getUsers();
			for (User user : users) {
				user.removeRole(role);
			}*/
			role.setResources(null);
		}
		this.deleteById(id);
		
		//删除Activiti角色
		identityService.deleteGroup(id.toString());
	}

	/**
	 * 批量删除角色 同时删掉用户 资源和角色的关联关系
	 * @param ids 角色Id集合
	 */
	@CacheEvict(value="shiroCache",allEntries=true)
	@Transactional(readOnly = false)
	public void batchDelete(Long[] ids) {
		for (Long id : ids) {
			delete(id);
		}
	}


	@CacheEvict(value="shiroCache",allEntries=true)
	@Transactional(readOnly = false)
	public void addResources(Long id, Long[] resourceIds) {
		Role role = this.findById(id);
		Set<Resource> resources = new HashSet<Resource>();
		for (Long resourceId : resourceIds) {
			Resource resource = new Resource();
			resource.setId(resourceId);
			resources.add(resource);
			role.setResources(resources);
		}
	}
	
	/**
	 * 更新角色对象，同时清除shiro缓存
	 * @param role 角色对象
	 */
	@CacheEvict(value="shiroCache",allEntries=true)
	@Transactional(readOnly = false)
	public Role updateRole(Role role) {
		return this.roleDao.save(role);
	}

	public Boolean isExistRoleName(String roleName) {
		Role role = this.roleDao.findByRoleName(roleName);
		return role != null;
	}
	
}


