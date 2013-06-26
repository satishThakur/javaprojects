package com.satish.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long m_id;
	
	private String m_name;
	
	private String m_description;
	
	private MonitoryAmount m_initialPrice;
	
	
	private List<Bid> m_bids = new ArrayList<Bid>();


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


	public void setDescription(String description) {
		m_description = description;
	}


	public String getDescription() {
		return m_description;
	}


	public void setInitialPrice(MonitoryAmount initialPrice) {
		m_initialPrice = initialPrice;
	}

	@org.hibernate.annotations.Type(type="com.satish.domain.MonitoryAmountUserType")
	public MonitoryAmount getInitialPrice() {
		return m_initialPrice;
	}


	public void setBids(List<Bid> bids) {
		m_bids = bids;
	}

	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="item")
	public List<Bid> getBids() {
		return m_bids;
	}
	
	public void addBid(Bid bid) {
		bid.setItem(this);
		m_bids.add(bid);		
	}
	

}
