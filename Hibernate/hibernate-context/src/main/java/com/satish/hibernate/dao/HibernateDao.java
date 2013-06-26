package com.satish.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface HibernateDao<T, ID extends Serializable> {
	
	public T findById(ID id, boolean lock);
	
	public List<T> findAll();
	
	public T makePersistent(T entity);
	
	public void makeTransient(T entity);
	
	public void flush();
	
	public void clear();

}
