package com.satish.test;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satich.usertype.dao.BidDao;
import com.satich.usertype.dao.BidDaoImpl;
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
		BidDao bidDao  = BidDaoImpl.getBidDaoInstance();

		bidDao.makePersistent(bid);

		t.commit();

		t=session.beginTransaction();
		List<Bid> bids = bidDao.findAll();

		for(Bid bid1 : bids) {
			System.out.println(bid1.getBidAmount());
			System.out.println(bid1.getBidAmount().convertTo("INR"));
		}
		t.commit();
	}

}
