package com.iscoreapp.dsels.screen;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.model.Quiz;

public class ResultsScreen extends BaseScreen {
	
	private TextView quizName;
	private TextView scoreView;
	private TextView wrongView;
	private TextView unansweredView;
	private TextView paperTotalView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_screen);
		initializeViews();
		
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setTitle(R.string.score_card);			
		}
		
		if (quiz == null || questions == null) {
			finish();
			return;
		}
		
		updateViews();
	}
	
	private void initializeViews() {
		quizName = (TextView) findViewById(R.id.quizName);
		scoreView = (TextView) findViewById(R.id.scoreView);
		wrongView = (TextView) findViewById(R.id.wrongView);
		unansweredView = (TextView) findViewById(R.id.unansweredView);
		paperTotalView = (TextView) findViewById(R.id.paperTotalView);
	}
	
	private void updateViews() {
		quizName.setText(quiz.getName());
		
		int score = Quiz.getScore(questions);
		int scoreCount = Quiz.getScoreCount(questions);
		scoreView.setText(score + " (" + scoreCount + ")");
		
		int wrong = Quiz.getWrong(questions);
		int wrongCount = Quiz.getWrongCount(questions);
		wrongView.setText(wrong + " (" + wrongCount + ")");
		
		int unanswered = Quiz.getUnanswered(questions);
		int unansweredCount = Quiz.getUnansweredCount(questions);
		unansweredView.setText(unanswered + " (" + unansweredCount + ")");
		
		int paperTotal = score + wrong + unanswered;
		int paperTotalCount = scoreCount + wrongCount + unansweredCount;
		paperTotalView.setText(paperTotal + " (" + paperTotalCount + ")");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			quiz = null;
			questions = null;
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
