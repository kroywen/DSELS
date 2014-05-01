package com.iscoreapp.dsels.screen;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.iscoreapp.dsels.R;

public class ResultsScreen extends BaseScreen implements OnClickListener {
	
	private TextView quizName;
	private TextView scoreView;
	private TextView wrongView;
	private TextView unansweredView;
	private TextView paperTotalView;
	private Button startNewBtn;
	
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
		
		if (quiz == null) {
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
		startNewBtn = (Button) findViewById(R.id.startNewBtn);
		startNewBtn.setOnClickListener(this);
	}
	
	private void updateViews() {
		quizName.setText(quiz.getName());
		
		int score = quiz.getScore();
		int scoreCount = quiz.getScoreCount();
		scoreView.setText(score + " (" + scoreCount + ")");
		
		int wrong = quiz.getWrong();
		int wrongCount = quiz.getWrongCount();
		wrongView.setText(wrong + " (" + wrongCount + ")");
		
		int unanswered = quiz.getUnanswered();
		int unansweredCount = quiz.getUnansweredCount();
		unansweredView.setText(unanswered + " (" + unansweredCount + ")");
		
		int paperTotal = score + wrong + unanswered;
		int paperTotalCount = scoreCount + wrongCount + unansweredCount;
		paperTotalView.setText(paperTotal + " (" + paperTotalCount + ")");
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

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.startNewBtn) {
			Intent intent = new Intent(this, MainScreen.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	}

}
