package com.xinyuan.haze.core.jpa.search;

import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

/**
 * JPA动态条件查询类  使用方法：将查询条件封装成queryVirables对象，queryVirables格式如key=name_LIKE,value="sofar"
 * @author Sofar
 *
 * @param <T>
 */
public class HazeSpecification<T> implements Specification<T> {

	private static final String SPLIT = "_";
	private Map<String, Object> queryVirables;
	
	public HazeSpecification(Map<String, Object>queryVirables) {
		this.queryVirables = queryVirables;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Predicate p = null;
		if (queryVirables != null) {
			Set<String> keys = queryVirables.keySet();
			for (String value : keys) {
				String[] keyAndOperator = getKeyAndOperator(value);
				String key = keyAndOperator[0];
				String operatator = keyAndOperator[1];
				Object queryValue = queryVirables.get(value);
				if (StringUtils.isNotEmpty(key)) {
					Predicate predicate = null;
					Path expression = null;
					try {
						 expression = root.get(key);
					} catch(Exception e) {
						break;
					}
					if ("eq".equals(operatator)) {
						predicate = cb.equal(expression, queryValue);
					} else if ("like".equals(operatator)) {
						predicate = cb.like(expression, "%" + queryValue + "%");
					} else if ("ge".equals(operatator)) {
						predicate = cb.ge(expression, (Number)queryValue);
					} else if ("le".equals(operatator)) {
						predicate = cb.le(expression, (Number)queryValue);
					} else if ("isNull".equals(operatator)) {
						predicate = cb.isNull(expression);
					} else if ("isNotNull".equals(operatator)) {
						predicate = cb.isNotNull(expression);
					} else if ("isTrue".equals(operatator)) {
						predicate = cb.isTrue(expression);
					} else if ("isFalse".equals(operatator)) {
						predicate = cb.isFalse(expression);
					} else if ("notEqual".equals(operatator)) {
						predicate = cb.notEqual(expression, queryValue);
					} else {
						predicate = cb.equal(expression, queryValue);
					}
					if (predicate != null) {
						if (p != null) {
							p = cb.and(predicate,p);
						} else {
							p = predicate;
						}
					}
				}
			}
		} 
		return p;
	}

	private String[] getKeyAndOperator(String key) {
		if (key.indexOf(SPLIT) == -1) {
			return new String[]{key,"equal"};
		}
		return key.split(SPLIT);
	}
}
