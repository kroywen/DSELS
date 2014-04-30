package com.iscoreapp.dsels.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

import com.iscoreapp.dsels.R;

public class MainScreen extends BaseScreen implements OnClickListener, OnValueChangeListener {
	
	private NumberPicker quizPicker;
	private TextView selectedQuizView;
	private Button startBtn;
	
	private static String[] quizList = new String[] {
		"SampleData1", "SampleData2", "SampleData3", "SampleData4", "SampleData5"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		initializeViews();
		populateQuizList();
	}
	
	private void initializeViews() {
		quizPicker = (NumberPicker) findViewById(R.id.quizPicker);
		quizPicker.setOnValueChangedListener(this);
		
		selectedQuizView = (TextView) findViewById(R.id.selectedQuizView);
		
		startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(this);
	}
	
	private void populateQuizList() {
		if (quizList != null && quizList.length != 0) {
			quizPicker.setMinValue(0);
			quizPicker.setMaxValue(quizList.length-1);
			quizPicker.setWrapSelectorWheel(false);
			quizPicker.setDisplayedValues(quizList);
			quizPicker.setValue(0);
			selectedQuizView.setText(quizList[0]);
		}
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		selectedQuizView.setText(quizList[newVal]);
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
		Intent intent = new Intent(this, IntroScreen.class);
		intent.putExtra(EXTRA_QUIZ_NAME, quizList[quizPicker.getValue()]);
		startActivity(intent);
	}

}
