package com.satish.usertype.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bid {

	private Long m_id;
	
	private String m_category;
	
	private String m_userName;
	
	private MonitoryAmount m_bidAmount;

	public void setId(Long id) {
		m_id = id;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return m_id;
	}

	public void setCategory(String category) {
		m_category = category;
	}

	public String getCategory() {
		return m_category;
	}

	public void setUserName(String userName) {
		m_userName = userName;
	}

	public String getUserName() {
		return m_userName;
	}

	public void setBidAmount(MonitoryAmount bidAmount) {
		m_bidAmount = bidAmount;
	}

	
	@org.hibernate.annotations.Type(type="com.satish.usertype.domain.MonitoryAmountUserType")
	public MonitoryAmount getBidAmount() {
		return m_bidAmount;
	}
	
	
	
}
