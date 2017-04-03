package bigdata.service;

import java.util.List;

public interface BaseService<T> {
	// 写操作
	public void saveEntity(T t);

	public void updateEntity(T t);

	public void deleteEntity(T t);

	public void executeByHQL(String sql, Object... objects);// 执行HQL

	// 读操作
	public T getEntity(Integer id);

	public List<T> finfByHQL(String sql, Object... objects);
}
