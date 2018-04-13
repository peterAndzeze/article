package com.article.recommend.vo;

import java.io.Serializable;

public class PersonVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Float value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public PersonVo(Long id, Float value) {
		super();
		this.id = id;
		this.value = value;
	}
	
}
