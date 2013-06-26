package com.satish.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.BankAccount;
import com.satish.domain.CreditCard;
import com.satish.domain.User;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = SessionFactoryHelper.getSession();
		Transaction t=session.beginTransaction();
		CreditCard cc = new CreditCard();

		cc.setOwner("Owner1");
		cc.setNumber("2345");
		cc.setExpiryMonth("Jan");
		cc.setExpiryYear("2015");

		BankAccount ba = new BankAccount();
		ba.setOwner("Owner2");
		ba.setBankAccount("90012");
		ba.setBankName("SBI");
		ba.setSwift("swift");

		session.save(cc);
		session.save(ba);
		
		User user1 = new User();
		user1.setName("John");
		user1.setBillingDetail(cc);
		
		User user2 = new User();
		user2.setName("Mary");
		user2.setBillingDetail(ba);
		
		session.save(user1);
		session.save(user2);
		t.commit();

	}

}
