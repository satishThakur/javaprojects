package com.satish.dao;

import org.hibernate.SessionFactory;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.Item;
import com.satish.hibernate.dao.GenericHibernateDao;

public class ItemDaoImpl extends GenericHibernateDao<Item, Long> {
	private static ItemDao m_itemDao = (ItemDao) new ItemDaoImpl(SessionFactoryHelper.getSessionFactory());
	
	public ItemDao getItemDao() {
		return m_itemDao;
	}	
	
	private ItemDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);		
	}

}
