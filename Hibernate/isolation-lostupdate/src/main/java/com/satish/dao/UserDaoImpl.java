package com.satish.dao;

import org.hibernate.SessionFactory;

import com.satish.domain.User;
import com.satish.hibernate.dao.GenericHibernateDao;

public class UserDaoImpl extends GenericHibernateDao<User, Long> implements
		UserDao {
	

	private UserDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		
	}	

}
