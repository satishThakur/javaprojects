package com.satish.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.satish.context.SessionFactoryHelper;
import com.satish.domain.Job;


public class TestLongData {
	
	public static void main(String[] args) throws IOException {
		Session session = SessionFactoryHelper.getSession();
		Transaction t=session.beginTransaction();
		
		Job job = new Job();
		
		byte[] data = getData(1000);
		job.setData(data);
		session.save(job);		
		t.commit();
		
		

	}

	private static byte[] getData(int max) throws IOException {
		Set<Long> ids = new HashSet<Long>();
		
		for(int i = 0; i < max ; i++) {
			ids.add((long)i);
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(ids);
		byte[] byteArray = baos.toByteArray();
		
		return byteArray;
		
	}

}
