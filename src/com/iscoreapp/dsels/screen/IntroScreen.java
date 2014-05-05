package com.iscoreapp.dsels.screen;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.api.ApiData;
import com.iscoreapp.dsels.api.ApiResponse;
import com.iscoreapp.dsels.model.Quiz;

public class IntroScreen extends BaseScreen implements OnClickListener {
	
	private String name;
	private String introLocation;
	private String dataLocation;
	
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
		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(introLocation) || TextUtils.isEmpty(dataLocation)) {
			finish();
			return;
		}
		
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setTitle(name);
		}
		
		if (isConnectionAvailable()) {
			remoteQuizProvider.loadQuizIntro(introLocation);
			showProgressDialog(getString(R.string.loading_quiz_intro));
		} else {
			quiz = localQuizProvider.loadQuizIntro(name);
			updateViews();
		}
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
			introLocation = intent.getStringExtra(EXTRA_INTRO_LOCATION);
			dataLocation = intent.getStringExtra(EXTRA_DATA_LOCATION);
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
		if (quiz != null) {
			quizName.setText(quiz.getName());
			quizType.setText(quiz.getType());
			questionCount.setText(String.valueOf(quiz.getQuestionCount()));
			allowedTime.setText(quiz.getAllowedTime());
		} else {
			showDialog(
				R.string.error, 
				R.string.quiz_empty,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
					}
				},
				new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						dialog.dismiss();
						finish();
					}
				}
			);
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, QuestionScreen.class);
		intent.putExtra(EXTRA_QUIZ_NAME, name);
		intent.putExtra(EXTRA_DATA_LOCATION, dataLocation);
		startActivity(intent);
		finish();
	}
	
	@Override
	public void onApiResponse(int apiStatus, ApiResponse apiResponse) {
		hideProgressDialog();
		if (apiResponse != null && ApiData.COMMAND_QUIZ_INTRO.equalsIgnoreCase(apiResponse.getRequestName())) {
			quiz = (Quiz) apiResponse.getData();
			localQuizProvider.storeQuizIntro(name, quiz);
			updateViews();
		}
	}

}
