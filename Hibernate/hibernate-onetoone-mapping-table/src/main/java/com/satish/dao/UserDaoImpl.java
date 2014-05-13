package com.satish.dao;

import org.hibernate.SessionFactory;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.User;
import com.satish.hibernate.dao.GenericHibernateDao;

public class UserDaoImpl extends GenericHibernateDao<User, Long> implements
		UserDao {
	
	private static UserDao m_itemDao = new UserDaoImpl(SessionFactoryHelper.getSessionFactory());
	
	public static UserDao getItemDao() {
		return m_itemDao;
	}

	private UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		
	}	

}
