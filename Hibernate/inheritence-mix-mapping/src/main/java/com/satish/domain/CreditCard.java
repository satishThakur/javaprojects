package com.satish.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(name = "CreditCard")
public class CreditCard extends BillingDetails{	
	
	private String m_number;
	
	private String m_expiryMonth;
	
	private String m_expiryYear;
	
	public void setNumber(String number) {
		m_number = number;
	}
	
	@Column(table="CreditCard")
	public String getNumber() {
		return m_number;
	}

	public void setExpiryMonth(String expiryMonth) {
		m_expiryMonth = expiryMonth;
	}

	@Column(table="CreditCard")
	public String getExpiryMonth() {
		return m_expiryMonth;
	}

	public void setExpiryYear(String expiryYear) {
		m_expiryYear = expiryYear;
	}

	@Column(table="CreditCard")
	public String getExpiryYear() {
		return m_expiryYear;
	}
	
	

}
