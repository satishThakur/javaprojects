package com.satish.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Data {

	private Long m_id;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return m_id;
	}

	private String m_name;

	private int m_number;

	private SomeOtherData m_otherData;

	public void setId(Long id) {
		m_id = id;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}

	public int getNumber() {
		return m_number;
	}

	public void setNumber(int number) {
		m_number = number;
	}

	public SomeOtherData getOtherData() {
		return m_otherData;
	}

	public void setOtherData(SomeOtherData otherData) {
		m_otherData = otherData;
	}

}
