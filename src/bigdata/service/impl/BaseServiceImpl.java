package bigdata.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bigdata.dao.BaseDao;
import bigdata.service.BaseService;
/**
 * 抽象service实现类，专门为service所用
 */
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> dao;
	
	@Resource
	public void setDao(BaseDao dao){
		this.dao=dao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public void executeByHQL(String sql, Object... objects) {
		dao.executeByHQL(sql, objects);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	public List<T> finfByHQL(String sql, Object... objects) {
		return dao.finfByHQL(sql, objects);
	}

}
