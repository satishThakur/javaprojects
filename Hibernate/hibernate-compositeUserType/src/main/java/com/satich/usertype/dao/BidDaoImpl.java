package com.satich.usertype.dao;

import org.hibernate.SessionFactory;

import com.satish.context.SessionFactoryHelper;
import com.satish.hibernate.dao.GenericHibernateDao;
import com.satish.usertype.domain.Bid;

public class BidDaoImpl extends GenericHibernateDao<Bid, Long> implements BidDao {
	private static BidDaoImpl m_bidDao = new BidDaoImpl(SessionFactoryHelper.getSessionFactory());
	
	public static BidDao getBidDaoInstance() {
		return m_bidDao;
	}
	
	private BidDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);		
	}

}
