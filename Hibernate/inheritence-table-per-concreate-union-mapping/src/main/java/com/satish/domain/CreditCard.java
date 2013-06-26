package com.satish.domain;

import javax.persistence.Entity;

@Entity
public class CreditCard extends BillingDetails{	
	
	private String m_number;
	
	private String m_expiryMonth;
	
	private String m_expiryYear;
	
	public void setNumber(String number) {
		m_number = number;
	}

	public String getNumber() {
		return m_number;
	}

	public void setExpiryMonth(String expiryMonth) {
		m_expiryMonth = expiryMonth;
	}

	public String getExpiryMonth() {
		return m_expiryMonth;
	}

	public void setExpiryYear(String expiryYear) {
		m_expiryYear = expiryYear;
	}

	public String getExpiryYear() {
		return m_expiryYear;
	}
	
	

}
