package com.satish.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.Address;
import com.satish.domain.User;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Session session = SessionFactoryHelper.getSession();
		
		Address address = new Address();
		
		address.setStreet("Street xyz");
		address.setZipcode("175024");
		address.setCity("Bangalore");
		
		User user = new User();
		
		user.setAddress(address);
		user.setName("Joe");
		
		 Transaction t=session.beginTransaction();
		 
		 session.save(user);
		 
		 t.commit();
		 
		 
		
		

	}

}
