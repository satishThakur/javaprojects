package com.satish.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long m_id;
	
	private String m_name;
	
	private String m_lastName;

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

	public String getLastName() {
		return m_lastName;
	}

	public void setLastName(String lastName) {
		m_lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "User: FirstName: " + String.valueOf(m_name) + " Last Name: " + String.valueOf(m_lastName);
	}

}
