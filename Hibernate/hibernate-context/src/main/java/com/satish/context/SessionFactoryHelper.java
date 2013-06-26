package com.satish.context;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionFactoryHelper {
	
	public static SessionFactory getSessionFactory() {
		return ApplicatioContextHelper.getBean("sessionFactory", SessionFactory.class);
	}
	
	public static Session getSession()
	{
		Session session=getSessionFactory().openSession();
		return session;
	}
	
	

}
