package com.satish.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.satish.dao.UserDao;
import com.satish.domain.User;

/**
 * 
 * @author satish
 *The service use explicit TransactionTemplate. we can choose to declaratively define the Tx as proxy.
 *Here just to make explicit what we are doing, choosen to be explicit.
 */
public class UserMgmtServiceImpl implements UserMgmtService {
	
	private static Logger m_logger = Logger.getLogger(UserMgmtServiceImpl.class);
	private TransactionTemplate m_txTemplate;
	private UserDao m_userDao;
	
	public UserMgmtServiceImpl(TransactionTemplate txTemplate, UserDao userDao){
		m_txTemplate = txTemplate;
		m_userDao = userDao;
	}

	@Override
	public User createUser(  final User user) {
		return m_txTemplate.execute(new TransactionCallback<User>() {

			@Override
			public User doInTransaction(TransactionStatus status) {
				return m_userDao.makePersistent(user);
			}
		});
	}

	@Override
	public User changeFirstName(final long userId, final String firstname) {
		return m_txTemplate.execute(new TransactionCallback<User>() {

			@Override
			public User doInTransaction(TransactionStatus status) {
				User user = m_userDao.findById(userId, false);
				if(user != null) {
					user.setName(firstname);
					return m_userDao.makePersistent(user);
				}else{
					m_logger.error("User with id " + userId + " does not exist");
					return null;
				}
			}
		});

	}

	@Override
	public User changeLastName(final long userId, final String lastName) {
		return m_txTemplate.execute(new TransactionCallback<User>() {
 
			@Override
			public User doInTransaction(TransactionStatus status) {
				User user = m_userDao.findById(userId, false);
				if(user != null) {
					user.setLastName(lastName);
					return m_userDao.makePersistent(user);
				}else{
					m_logger.error("User with id " + userId + " does not exist");
					return null;
				}
			}
		});
	}

	@Override
	public void logAllUsers() {
		m_txTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				List<User> users = m_userDao.findAll();
				for(User user : users){
					m_logger.info("User: " + user);
				}
				
			}
		});

	}

	@Override
	public void logUser(final long userId) {
		m_txTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				User user = m_userDao.findById(userId, false);
				if(user != null) {
					m_logger.info("User: " + user);
				}else{
					m_logger.error("User with id " + userId + " does not exist");
				}
				
			}
		});

	}

	@Override
	public void deleteAll() {
		m_txTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				List<User> users = m_userDao.findAll();
				for(User user : users){
					m_logger.info("Deleting User: " + user);
					m_userDao.makeTransient(user);
				}
			}
		});
		
	}

}
