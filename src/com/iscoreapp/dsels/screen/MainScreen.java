package com.iscoreapp.dsels.screen;

import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.api.ApiData;
import com.iscoreapp.dsels.api.ApiResponse;
import com.iscoreapp.dsels.model.QuizListItem;
import com.iscoreapp.dsels.util.Utilities;

public class MainScreen extends BaseScreen implements OnClickListener, OnValueChangeListener {
	
	private NumberPicker quizPicker;
	private TextView selectedQuizView;
	private Button startBtn;
	
	private List<QuizListItem> quizList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		initializeViews();

		boolean needUpdate = getIntent().getBooleanExtra("needUpdate", false); 
		if (isConnectionAvailable() && needUpdate) {
			remoteQuizProvider.loadQuizList();
			showProgressDialog(getString(R.string.loading_quiz_list));
		} else {
			quizList = localQuizProvider.loadQuizList();
			populateQuizList();
		}
	}
	
	private void initializeViews() {
		quizPicker = (NumberPicker) findViewById(R.id.quizPicker);
		quizPicker.setOnValueChangedListener(this);
		
		selectedQuizView = (TextView) findViewById(R.id.selectedQuizView);
		
		startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(this);
	}
	
	private void populateQuizList() {
		if (quizList != null && !quizList.isEmpty()) {
			quizPicker.setMinValue(0);
			quizPicker.setMaxValue(quizList.size()-1);
			quizPicker.setWrapSelectorWheel(false);
			quizPicker.setDisplayedValues(Utilities.listAsStringArray(quizList));
			quizPicker.setValue(0);
			
			QuizListItem selectedQuiz = quizList.get(0);
			selectedQuizView.setText(selectedQuiz.getName() + ": " + selectedQuiz.getDescription());
		} else {
			showDialog(
				R.string.error, 
				R.string.quiz_list_empty,
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
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		QuizListItem selectedQuiz = quizList.get(newVal);
		selectedQuizView.setText(selectedQuiz.getName() + ": " + selectedQuiz.getDescription());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startBtn:
			startQuizIntroScreen();
			break;
		}
	}
	
	private void startQuizIntroScreen() {
		QuizListItem quizItem = quizList.get(quizPicker.getValue());
		Intent intent = new Intent(this, IntroScreen.class);
		intent.putExtra(EXTRA_QUIZ_NAME, quizItem.getName());
		intent.putExtra(EXTRA_INTRO_LOCATION, quizItem.getIntroLocation());
		intent.putExtra(EXTRA_DATA_LOCATION, quizItem.getDataLocation());
		startActivity(intent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onApiResponse(int apiStatus, ApiResponse apiResponse) {
		hideProgressDialog();
		if (apiResponse != null && ApiData.COMMAND_QUIZ_LIST.equalsIgnoreCase(apiResponse.getRequestName())) {
			quizList = (List<QuizListItem>) apiResponse.getData();
			localQuizProvider.storeQuizList(quizList);
			populateQuizList();
		}
	}

}
