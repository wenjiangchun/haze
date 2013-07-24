package com.xinyuan.haze.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.xinyuan.haze.core.jpa.repository.BaseRepository;
import com.xinyuan.haze.core.jpa.search.HazeSpecification;

/**
 * 业务处理类抽象类   系统中所有业务处理类继承该类
 * @param <T> 实体类对象
 * @param <ID> 业务主键类型
 * @author Sofar
 */
public  abstract class AbstractBaseService<T, ID extends Serializable> {

	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BaseRepository<T,ID> dao;
	
	/**
	 *  在子类实现类注入函数中调用该方法注入dao 示例如下:
	 *  @Autowired
	 *	public void setGroupDao(GroupDao groupDao) {
	 *		this.groupDao = groupDao;
	 *		super.setDao(groupDao);
	 *	}
	 * @param dao
	 */
	public void setDao(BaseRepository<T,ID> dao) {
		this.dao = dao;
	}
	
	/**
	 * 查询所有对象
	 * @return T 对象集合
	 */
	public List<T> findAll() {
		return (List<T>) this.dao.findAll();
	}

	/**
	 * 根据Id查找对象
	 * @param id 对象Id
	 * @return T 对象
	 */
	public T findById(ID id) {
		return this.dao.findOne(id);
	}

	/**
	 * 保存 T 对象
	 * @param t 对象
	 * @return T 对象
	 */
	@Transactional(readOnly = false)
	public T save(T t) {
		return this.dao.save(t);
	}

	@Transactional(readOnly = false)
	public void delete(T t) {
		 this.dao.delete(t);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(ID id) {
		 this.dao.delete(id);
	}
	
	/**
	 * 根据Id数组批量删除对象
	 * @param ids Id数组
	 */
	@Transactional(readOnly = false)
	public void batchDelete(ID[] ids) {
		for (ID id : ids) {
			deleteById(id);
		}
	}
	
	/**
	 * 分页查询 <T> 列表对象
	 * @param p 分页对象
	 * @return 分页<T>列表对象
	 */
	public Page<T> findPage(Pageable p) {
		return this.dao.findAll(p);
	}
	
	/**
	 * 根据查询参数分页查询列表信息
	 * @param queryVirables 查询参数
	 * @param pageable 分页对象
	 * @return 分页对象
	 */
	public Page<T> findPage(Pageable pageable, Map<String, Object> queryVirables) {
		Specification<T> spec = new HazeSpecification<>(queryVirables);
		return this.dao.findAll(spec, pageable);
	}
	
	/**
	 * 根据查询参数查询所有<T>对象信息
	 * @param queryVirables 查询参数
	 * @return 所有符合条件<T>对象
	 */
	public List<T> findAll(Map<String, Object> queryVirables) {
		Specification<T> spec = new HazeSpecification<>(queryVirables);
		return this.dao.findAll(spec);
	}
}
