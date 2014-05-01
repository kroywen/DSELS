package com.iscoreapp.dsels.screen;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.iscoreapp.dsels.R;

public class IntroScreen extends BaseScreen implements OnClickListener {
	
	private String name;
	
	private TextView quizName;
	private TextView quizType;
	private TextView questionCount;
	private TextView allowedTime;
	private Button startBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro_screen);
		initializeViews();
		
		getIntentData();
		if (TextUtils.isEmpty(name)) {
			finish();
			return;
		}
		
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setTitle(name);
		}
		
		quiz = quizProvider.loadQuiz(name);
		updateViews();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void getIntentData() {
		Intent intent = getIntent();
		if (intent != null) {
			name = intent.getStringExtra(EXTRA_QUIZ_NAME);
		}
	}
	
	private void initializeViews() {
		quizName = (TextView) findViewById(R.id.quizName);
		quizType = (TextView) findViewById(R.id.quizType);
		questionCount = (TextView) findViewById(R.id.questionCount);
		allowedTime = (TextView) findViewById(R.id.allowedTime);
		startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(this);
	}
	
	private void updateViews() {
		if (quiz == null) {
			return;
		}
		quizName.setText(quiz.getName());
		quizType.setText(quiz.getType());
		questionCount.setText(String.valueOf(quiz.getQuestionCount()));
		allowedTime.setText(quiz.getAllowedTime());
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, QuestionScreen.class);
		startActivity(intent);
		finish();
	}

}
