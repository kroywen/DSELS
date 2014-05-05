package com.iscoreapp.dsels.model;

import java.io.Serializable;

public class QuizListItem implements Serializable {
	
	private static final long serialVersionUID = -6569211346508660901L;
	
	private String name;
	private String description;
	private String introLocation;
	private String dataLocation;
	
	public QuizListItem() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntroLocation() {
		return introLocation;
	}

	public void setIntroLocation(String introLocation) {
		this.introLocation = introLocation;
	}

	public String getDataLocation() {
		return dataLocation;
	}

	public void setDataLocation(String dataLocation) {
		this.dataLocation = dataLocation;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
