package com.satish.test;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.Bid;
import com.satish.domain.Item;
import com.satish.domain.MonitoryAmount;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = SessionFactoryHelper.getSession();
		Transaction t=session.beginTransaction();
		MonitoryAmount amount = new MonitoryAmount(new BigDecimal(4000), "USD");
		
		Item item = new Item();
		
		item.setName("iphone-5");
		item.setDescription("Mobile Phone");
		item.setInitialPrice(amount);
		
		MonitoryAmount bidAmount = new MonitoryAmount(new BigDecimal(4000), "USD");
		Bid bid = new Bid();
		bid.setBidAmount(bidAmount);	
		
		item.addBid(bid);
		
		session.save(item);
		
		
		
		System.out.println("Item Id: " + item.getId());
		System.out.println("Bid Id: " + bid.getId());
		t.commit();
		session.close();
		

	}

}
