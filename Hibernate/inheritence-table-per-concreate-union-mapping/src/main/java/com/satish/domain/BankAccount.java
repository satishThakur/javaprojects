package com.satish.domain;

import javax.persistence.Entity;

@Entity
public class BankAccount extends BillingDetails {
	
	private String m_bankAccount;
	
	private String m_bankName;
	
	private String m_swift;	

	
	public void setBankAccount(String bankAccount) {
		m_bankAccount = bankAccount;
	}

	public String getBankAccount() {
		return m_bankAccount;
	}

	public void setBankName(String bankName) {
		m_bankName = bankName;
	}

	public String getBankName() {
		return m_bankName;
	}

	public void setSwift(String swift) {
		m_swift = swift;
	}

	public String getSwift() {
		return m_swift;
	}
	
	
}
