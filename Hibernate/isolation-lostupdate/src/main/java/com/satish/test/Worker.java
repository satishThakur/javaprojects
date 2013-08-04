package com.satish.test;

import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class Worker implements Runnable {
	
	private static Logger m_logger = Logger.getLogger(Worker.class);
	Workdevider m_workdevider;
	private Semaphore m_lock;
	private TransactionTemplate m_txTemplate;
	
	//make it volatile is some other thread reads it too..
	//no need to make it sync as only one thread writes.
	private volatile boolean m_isFinished = false;
	
	public Worker(Workdevider workdevider, Semaphore lock,TransactionTemplate txTemplate){
		m_workdevider = workdevider;
		m_lock = lock;
		m_txTemplate = txTemplate;
	}

	@Override
	public void run() {
		m_logger.info("Running first part");
		
		m_txTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				m_workdevider.doFirst();
				m_logger.info("Running first part Done... waiting for lock...");
				try {
					m_lock.acquire();
				} catch (InterruptedException e) {
					m_logger.error("interrupt");
				}
				m_logger.info("got the lock.. running second part");
				m_workdevider.doLast();
				m_isFinished = true;
				m_logger.info("second part done...");
			}
		});		

	}
	
	public boolean isFinished(){
		return m_isFinished;
	}

}
