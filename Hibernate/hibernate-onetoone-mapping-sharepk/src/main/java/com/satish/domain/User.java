package com.satish.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long m_id;
	
	private String m_name;
	
	private boolean m_isAdmin;
	
	private Address m_address;

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

	public void setAdmin(boolean isAdmin) {
		m_isAdmin = isAdmin;
	}

	public boolean isAdmin() {
		return m_isAdmin;
	}

	public void setAddress(Address address) {
		m_address = address;
		address.setUser(this);
	}
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")		
	public Address getAddress() {
		return m_address;
	}

}
