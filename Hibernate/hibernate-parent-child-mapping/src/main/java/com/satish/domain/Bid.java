package com.satish.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {
	
	private Long m_id;
	
	private Item m_item;
	
	private MonitoryAmount m_bidAmount;

	public void setId(Long id) {
		m_id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return m_id;
	}

	public void setItem(Item item) {
		m_item = item;
	}

	@ManyToOne
	public Item getItem() {
		return m_item;
	}

	public void setBidAmount(MonitoryAmount bidAmount) {
		m_bidAmount = bidAmount;
	}

	@org.hibernate.annotations.Type(type="com.satish.domain.MonitoryAmountUserType")
	public MonitoryAmount getBidAmount() {
		return m_bidAmount;
	}	

}
