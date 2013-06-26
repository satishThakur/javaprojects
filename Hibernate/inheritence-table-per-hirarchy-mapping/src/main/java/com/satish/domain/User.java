package com.satish.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {
	
	private Long m_id;
	
	private String m_name;
	
	private BillingDetails m_billingDetail;

	public void setId(Long id) {
		m_id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return m_id;
	}

	public void setName(String name) {
		m_name = name;
	}
	
	
	public String getName() {
		return m_name;
	}

	public void setBillingDetail(BillingDetails billingDetail) {
		m_billingDetail = billingDetail;
	}
	
	@ManyToOne
	public BillingDetails getBillingDetail() {
		return m_billingDetail;
	}

	

}
