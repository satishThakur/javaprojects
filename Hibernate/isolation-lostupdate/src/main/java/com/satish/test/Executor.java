package com.satish.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class Executor {
	private static Logger m_logger = Logger.getLogger(Executor.class);
	private ExecutorService m_exservice = Executors.newCachedThreadPool();
	
	public void executeJob(Runnable job){
		try{
		m_exservice.execute(job);
		}catch(Exception ex){
			m_logger.error("error executing job" + job, ex);
		}
	}
	
	public void shutDown(){
		m_logger.info("Shutting down Executor Service");
		m_exservice.shutdown();
	}

}
