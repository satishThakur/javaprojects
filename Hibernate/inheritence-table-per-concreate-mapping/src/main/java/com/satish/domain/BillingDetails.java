package com.satish.domain;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BillingDetails {	
	
	private String m_owner;
	
	public void setOwner(String owner) {
		m_owner = owner;
	}

	public String getOwner() {
		return m_owner;
	}
	
	
}
