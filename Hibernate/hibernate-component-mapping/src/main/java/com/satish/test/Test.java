package com.satish.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.Image;
import com.satish.domain.User;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = SessionFactoryHelper.getSession();
		Transaction t=session.beginTransaction();
		
		Image image = new Image();
		
		image.setName("Image1");
		image.setType("JPEG");
		image.setSize("5MB");
		
		
		User user = new User();
		
		user.setName("user1");
		
		user.addImage(image);
		session.save(user);
		t.commit();
		session.close();

	}

}
