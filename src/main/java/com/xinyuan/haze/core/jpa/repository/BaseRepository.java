package com.xinyuan.haze.core.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 系统Repository标记接口定义  该Repository接口继承JpaRepository和JpaSpecificationExecutor接口
 * @author Sofar
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>  {

}
