package com.satish.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.Data;
import com.satish.domain.SomeOtherData;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Session session = SessionFactoryHelper.getSession();
		
		Data data = new Data();
		data.setName("dataname");
		data.setNumber(100);
		
		SomeOtherData otherData = new SomeOtherData(); 
		otherData.setName("otherDataName");
		otherData.setType("otherDataType");
		
		data.setOtherData(otherData);
		
		Transaction t= session.beginTransaction();
		
		session.save(data);
		
		t.commit();
		session.close();
		
		
		 
		 
		
		

	}

}
