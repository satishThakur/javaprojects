package com.satish.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String m_street;
	
	private String m_zipcode;
	
	private String m_city;

	public void setStreet(String street) {
		m_street = street;
	}

	public String getStreet() {
		return m_street;
	}

	public void setZipcode(String zipcode) {
		m_zipcode = zipcode;
	}

	public String getZipcode() {
		return m_zipcode;
	}

	public void setCity(String city) {
		m_city = city;
	}

	public String getCity() {
		return m_city;
	}
	
	
}
