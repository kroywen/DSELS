package com.iscoreapp.dsels.provider;

import com.iscoreapp.dsels.model.Quiz;

public abstract class QuizProvider {
	
	public static final int TYPE_MOCK = 0;
	public static final int TYPE_REMOTE = 1;
	public static final int TYPE_LOCAL = 2;
	
	public abstract String[] loadtQuizNameList();
	
	public abstract Quiz loadQuiz(String name);
	
	public static QuizProvider newInstance(int type) {
		switch (type) {
		case TYPE_MOCK:
			return new MockQuizProvider();
		case TYPE_LOCAL:
			return new LocalQuizProvider();
		case TYPE_REMOTE:
			return new RemoteQuizProvider();
		default:
			return null;
		}
	}

}
