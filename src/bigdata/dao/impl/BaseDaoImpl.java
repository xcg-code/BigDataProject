package bigdata.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import bigdata.dao.BaseDao;
/**
 * 抽象的Dao实现，专门继承
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource
	private SessionFactory sf;
	private Class clazz;
	
	//构造函数
	public BaseDaoImpl() {
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class) type.getActualTypeArguments()[0];
	}
	
	public void saveEntity(T t) {
		sf.getCurrentSession().save(t);
	}
	public void updateEntity(T t) {
		sf.getCurrentSession().update(t);
	}
	public void deleteEntity(T t) {
		sf.getCurrentSession().delete(t);
	}

	//批量写操作，例：update users set name=? where id=?
	public void executeByHQL(String sql, Object... objects) {
		Query q=sf.getCurrentSession().createQuery(sql);
		for(int i=0;i<objects.length;i++){
			q.setParameter(i, objects[i]);	//绑定参数
		}
		q.executeUpdate();
	}

	public T getEntity(Integer id) {
		return (T)sf.getCurrentSession().get(clazz,id);
	}
	
	public List<T> findByHQL(String hql, Object... objects) {
		Query q=sf.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			q.setParameter(i, objects[i]);	//绑定参数
		}
		return q.list();
	}
}
