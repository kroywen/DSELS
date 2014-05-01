package com.iscoreapp.dsels.model;

import java.io.Serializable;

import android.text.TextUtils;

public class Question implements Serializable {
	
	private static final long serialVersionUID = 3502361698954072978L;
	
	private String instructions;
	private String question;
	private int weight;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String answer;
	private String userInput;
	
	public Question() {}
	
	public Question(String instructions, String question, int weight,
			String choice1, String choice2, String choice3, String choice4,
			String answer, String userInput) {
		this.instructions = instructions;
		this.question = question;
		this.weight = weight;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.answer = answer;
		this.userInput = userInput;
	}

	public String getInstructions() {
		return instructions;
	}
	
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getChoice1() {
		return choice1;
	}
	
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}
	
	public boolean hasChoice1() {
		return !(TextUtils.isEmpty(choice1) || choice1.equals("NA"));
	}
	
	public String getChoice2() {
		return choice2;
	}
	
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}
	
	public boolean hasChoice2() {
		return !(TextUtils.isEmpty(choice2) || choice2.equals("NA"));
	}
	
	public String getChoice3() {
		return choice3;
	}
	
	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}
	
	public boolean hasChoice3() {
		return !(TextUtils.isEmpty(choice3) || choice3.equals("NA"));
	}
	
	public String getChoice4() {
		return choice4;
	}
	
	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}
	
	public boolean hasChoice4() {
		return !(TextUtils.isEmpty(choice4) || choice4.equals("NA"));
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getUserInput() {
		return userInput;
	}
	
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	
	public boolean hasUserInput() {
		return !TextUtils.isEmpty(userInput);
	}
	
	public boolean isUserInputCorrect() {
		if (!hasUserInput()) {
			return false;
		}
		return answer.equals(userInput);
	}

}
