package com.satish.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.BankAccount;
import com.satish.domain.CreditCard;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = SessionFactoryHelper.getSession();

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

		Transaction t=session.beginTransaction();

		t.commit();

	}

}
