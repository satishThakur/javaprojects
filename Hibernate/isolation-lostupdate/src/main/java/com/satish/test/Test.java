package com.satish.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.satish.context.ApplicatioContextHelper;
import com.satish.domain.User;
import com.satish.service.UserMgmtService;

public class Test {
	
	private static Logger m_logger = Logger.getLogger(Test.class);

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		UserMgmtService userMgmtService = ApplicatioContextHelper.getBean("userMgmtService", UserMgmtService.class);
		TransactionTemplate txTemplate = ApplicatioContextHelper.getBean("transactionTemplate", TransactionTemplate.class);
		
		//clean the db
		userMgmtService.deleteAll();
		
		//insert one Entry
		User user = new User();		
		user.setName("User1");
		user.setLastName("lastname");
		user = userMgmtService.createUser(user);
		
		final long userId = user.getId();
		m_logger.info("Found user id " + userId);
		
		//launch a separate thread to change last name
		ReadAndUpdater workDevider = new ReadAndUpdater(userId);
		Semaphore lock = new Semaphore(0);
		Worker worker = new Worker(workDevider, lock, txTemplate);
		Executor ex = new Executor();
		ex.executeJob(worker);
		
		//makes the name as Satish
		user = userMgmtService.changeFirstName(userId, "satish");
		m_logger.info("User after saving in main thread: " + user);
		
		m_logger.info("RELEASING LOCK...");
		lock.release();
		
		//this is just to print and prove that main thread save is lost!!!
		boolean flag = true;
		while(flag){
			if(worker.isFinished()){
				flag = false;
				m_logger.info("Worker finished...");
				userMgmtService.logUser(userId);
				ex.shutDown();
			}
			TimeUnit.SECONDS.sleep(2);
			
		}

	}

}
