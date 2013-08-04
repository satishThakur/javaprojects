package com.satish.test;

import org.apache.log4j.Logger;

import com.satish.context.ApplicatioContextHelper;
import com.satish.dao.UserDao;
import com.satish.domain.User;

public class ReadAndUpdater implements Workdevider {
	
	private static Logger m_logger = Logger.getLogger(ReadAndUpdater.class);
	
	private Long m_userId;
	private User m_user;
	
	private UserDao m_userdao = ApplicatioContextHelper.getBean("userDao", UserDao.class);
	
	public ReadAndUpdater(Long userId){
		m_userId = userId;
	}

	@Override
	public void doFirst() {
		m_logger.info("Getting the User in session scope...");
		m_user = m_userdao.findById(m_userId,false);
		m_logger.info("Loaded User: " + m_user);

	}

	@Override
	public void doLast() {
		m_logger.info("Second part: " + m_user);
		m_user.setLastName("thakur");
		m_userdao.makePersistent(m_user);
	}

}
