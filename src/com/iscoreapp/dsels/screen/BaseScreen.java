package com.iscoreapp.dsels.screen;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.model.Quiz;
import com.iscoreapp.dsels.provider.QuizProvider;

public class BaseScreen extends FragmentActivity {
	
	public static final String EXTRA_QUIZ_NAME = "quiz_name";
	
	protected QuizProvider quizProvider;
	protected static Quiz quiz;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setTitle(R.string.app_title);
		}
		
		quizProvider = QuizProvider.newInstance(QuizProvider.TYPE_MOCK); // TODO change type to TYPE_REMOTE
	}

}
