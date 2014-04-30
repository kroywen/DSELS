package com.iscoreapp.dsels.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
	
	private String name;
	private String type;
	private String introLocation;
	private String dataLocation;
	private int questionCount;
	private String allowedTime;
	private String updated;
	private List<Question> questions;
	
	public Quiz() {
		questions = new ArrayList<Question>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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
	
	public int getQuestionCount() {
		return questionCount;
	}
	
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	
	public String getAllowedTime() {
		return allowedTime;
	}
	
	public void setAllowedTime(String allowedTime) {
		this.allowedTime = allowedTime;
	}
	
	public String getUpdated() {
		return updated;
	}
	
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
		if (questions == null) {
			questions = new ArrayList<Question>();
		}
		questions.add(question);
	}
	
	@Override
	public String toString() {
		return name;
	}

}
