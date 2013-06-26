package com.satish.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long m_id;
	
	private String m_street;
	
	private String m_city;
	
	private String m_pinCode;
	
	private User m_user;

	public void setStreet(String street) {
		m_street = street;
	}

	public String getStreet() {
		return m_street;
	}

	public void setCity(String city) {
		m_city = city;
	}

	public String getCity() {
		return m_city;
	}

	public void setPinCode(String pinCode) {
		m_pinCode = pinCode;
	}

	public String getPinCode() {
		return m_pinCode;
	}

	public void setId(Long id) {
		m_id = id;
	}

	
	@Id 
	@GeneratedValue	
	public Long getId() {
		return m_id;
	}

	public void setUser(User user) {
		m_user = user;
	}
	
	@OneToOne
	@JoinTable(name = "User_Address", inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID"))
	public User getUser() {
		return m_user;
	}	

}
