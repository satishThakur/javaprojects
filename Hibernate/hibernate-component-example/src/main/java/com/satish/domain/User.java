package com.satish.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	private Long m_id;
	
	private String m_name;
	
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
	
	@Column(length = 4000)
	public String getName() {
		return m_name;
	}

	public void setAddress(Address address) {
		m_address = address;
	}
	
	@Embedded
	public Address getAddress() {
		return m_address;
	}

}
