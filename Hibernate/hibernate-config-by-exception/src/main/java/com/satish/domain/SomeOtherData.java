package com.satish.domain;

import java.io.Serializable;

public class SomeOtherData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String m_name;
	
	private String m_type;

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

}
