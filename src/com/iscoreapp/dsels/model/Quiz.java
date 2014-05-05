package com.iscoreapp.dsels.model;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
	
	private static final long serialVersionUID = -8215672211291945520L;
	
	private String name;
	private String type;
	private int questionCount;
	private String allowedTime;
	private String updated;
	
	public Quiz() {}
	
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
	
	@Override
	public String toString() {
		return name;
	}
	
	public static int getScore(List<Question> questions) {
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
	
	public static int getScoreCount(List<Question> questions) {
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
	
	public static int getWrong(List<Question> questions) {
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
	
	public static int getWrongCount(List<Question> questions) {
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
	
	public static int getUnanswered(List<Question> questions) {
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
	
	public static int getUnansweredCount(List<Question> questions) {
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
