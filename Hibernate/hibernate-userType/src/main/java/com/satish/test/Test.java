package com.satish.test;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.usertype.domain.Bid;
import com.satish.usertype.domain.MonitoryAmount;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = SessionFactoryHelper.getSession();
		Transaction t=session.beginTransaction();
		MonitoryAmount amount = new MonitoryAmount(new BigDecimal(4000), "USD");
		Bid bid = new Bid();
		bid.setBidAmount(amount);
		bid.setCategory("TV");
		bid.setUserName("satish123");
		session.save(bid);
		
		t.commit();
		
		Bid bid1 = (Bid) session.get(Bid.class, 1L);
		System.out.println(bid1.getBidAmount());
		System.out.println(bid1.getBidAmount().convertTo("INR"));

	}

}
