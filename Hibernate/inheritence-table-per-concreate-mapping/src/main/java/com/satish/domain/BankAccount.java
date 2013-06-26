package com.satish.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankAccount extends BillingDetails {
	
	private Long m_id;

	private String m_bankAccount;
	
	private String m_bankName;
	
	private String m_swift;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return m_id;
	}

	public void setId(Long id) {
		m_id = id;
	}

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
