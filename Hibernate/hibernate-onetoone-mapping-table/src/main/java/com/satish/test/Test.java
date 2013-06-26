package com.satish.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.Address;
import com.satish.domain.User;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Session session = SessionFactoryHelper.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Address address = new Address();
		address.setStreet("XYZ Street");
		address.setCity("Bangalore");
		address.setPinCode("560034");
		
		
		User user = new User();
		
		user.setName("User1");
		user.setAdmin(false);
		user.setAddress(address);		
		session.saveOrUpdate(user);				
		tx.commit();
		
		session.close();
		

	}

}
