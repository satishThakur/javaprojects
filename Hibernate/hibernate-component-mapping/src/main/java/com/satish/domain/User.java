package com.satish.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class User {
	
	private Long m_id;
	
	private String m_name;
	
	private List<Image> m_images = new ArrayList<Image>();

	public void setId(Long id) {
		m_id = id;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return m_id;
	}

	public void setName(String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public void setImages(List<Image> images) {
		m_images = images;
	}
	
	@ElementCollection
	//@CollectionId(columns = @Column(name = "image_id"), generator = "sequence", type = @org.hibernate.annotations.Type(type = "long"))
	public List<Image> getImages() {
		return m_images;
	}

	public void addImage(Image image) {
		m_images.add(image);
		
	}

}
