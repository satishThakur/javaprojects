package com.satish.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long m_id;
	
	private byte[] m_data;

	public void setData(byte[] data) {
		m_data = data;
	}

	public byte[] getData() {
		return m_data;
	}

	public void setId(Long id) {
		m_id = id;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return m_id;
	}

}
