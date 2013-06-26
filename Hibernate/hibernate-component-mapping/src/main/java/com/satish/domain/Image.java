package com.satish.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Image implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String m_name;
	
	private String m_type;
	
	private String m_size;

	public void setName(String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public void setType(String type) {
		m_type = type;
	}

	public String getType() {
		return m_type;
	}

	public void setSize(String size) {
		m_size = size;
	}

	public String getSize() {
		return m_size;
	}
	
	

}
