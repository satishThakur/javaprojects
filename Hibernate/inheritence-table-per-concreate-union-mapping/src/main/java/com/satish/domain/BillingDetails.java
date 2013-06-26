package com.satish.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {	
	
	private Long m_id;
	
	private String m_owner;
	
	public void setId(Long id) {
		m_id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long getId() {
		return m_id;
	}
	
	public void setOwner(String owner) {
		m_owner = owner;
	}

	public String getOwner() {
		return m_owner;
	}
	
}
