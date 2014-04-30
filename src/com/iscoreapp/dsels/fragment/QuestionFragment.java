package com.iscoreapp.dsels.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.model.Question;

public class QuestionFragment extends Fragment {
	
	public static final String EXTRA_QUESTION = "question";
	public static final String EXTRA_QUESTION_NUMBER = "question_number";
	
	private TextView questionView;
	private RadioGroup choiceGroup;
	private RadioButton choice1Btn;
	private RadioButton choice2Btn;
	private RadioButton choice3Btn;
	private RadioButton choice4Btn;
	private TextView userInput;
	
	private int questionNumber;
	private Question question;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		questionNumber = args.getInt(EXTRA_QUESTION_NUMBER);
		question = (Question) args.getSerializable(EXTRA_QUESTION);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.question_fragment, null);
		initializeViews(rootView);
		return rootView;
	}
	
	private void initializeViews(View rootView) {
		questionView = (TextView) rootView.findViewById(R.id.questionView);
		questionView.setText(questionNumber + ". " + question.getQuestion());
		
		choiceGroup = (RadioGroup) rootView.findViewById(R.id.choiceGroup);
		choice1Btn = (RadioButton) rootView.findViewById(R.id.choice1Btn);
		choice1Btn.setText(question.getChoice1());
		choice2Btn = (RadioButton) rootView.findViewById(R.id.choice2Btn);
		choice2Btn.setText(question.getChoice2());
		choice3Btn = (RadioButton) rootView.findViewById(R.id.choice3Btn);
		choice3Btn.setText(question.getChoice3());
		choice4Btn = (RadioButton) rootView.findViewById(R.id.choice4Btn);
		choice4Btn.setText(question.getChoice4());
		
		userInput = (TextView) rootView.findViewById(R.id.userInput);
		userInput.setText(getString(R.string.user_input_format, question.getUserInput()));
	}

}
