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
	
	public int getScore() {
		if (questions == null || questions.isEmpty()) {
			return 0;
		}
		int score = 0;
		for (Question question : questions) {
			if (question.isUserInputCorrect()) {
				score += question.getWeight();
			}
		}
		return score;
	}
	
	public int getScoreCount() {
		if (questions == null || questions.isEmpty()) {
			return 0;
		}
		int scoreCount = 0;
		for (Question question : questions) {
			if (question.isUserInputCorrect()) {
				scoreCount++;
			}
		}
		return scoreCount;
	}
	
	public int getWrong() {
		if (questions == null || questions.isEmpty()) {
			return 0;
		}
		int wrong = 0;
		for (Question question : questions) {
			if (question.hasUserInput() && !question.isUserInputCorrect()) {
				wrong += question.getWeight();
			}
		}
		return wrong;
	}
	
	public int getWrongCount() {
		if (questions == null || questions.isEmpty()) {
			return 0;
		}
		int wrongCount = 0;
		for (Question question : questions) {
			if (question.hasUserInput() && !question.isUserInputCorrect()) {
				wrongCount++;
			}
		}
		return wrongCount;
	}
	
	public int getUnanswered() {
		if (questions == null || questions.isEmpty()) {
			return 0;
		}
		int unanswered = 0;
		for (Question question : questions) {
			if (!question.hasUserInput()) {
				unanswered += question.getWeight();
			}
		}
		return unanswered;
	}
	
	public int getUnansweredCount() {
		if (questions == null || questions.isEmpty()) {
			return 0;
		}
		int unansweredCount = 0;
		for (Question question : questions) {
			if (!question.hasUserInput()) {
				unansweredCount++;
			}
		}
		return unansweredCount;
	}

}
