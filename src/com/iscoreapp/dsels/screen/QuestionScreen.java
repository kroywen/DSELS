package com.iscoreapp.dsels.screen;

import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.fragment.QuestionFragment;
import com.iscoreapp.dsels.model.Question;

public class QuestionScreen extends BaseScreen implements OnClickListener, OnPageChangeListener {
	
	private int questionNumber;
	private List<Question> questions;
	
	private ViewPager viewPager;
	private Button backBtn;
	private Button nextBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_screen);
		initializeViews();
		
		if (quiz == null) {
			finish();
			return;
		}

		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setTitle(quiz.getName());
		}
		
		questions = quiz.getQuestions();
		if (questions == null || questions.isEmpty()) {
			finish();
			return;
		}
		questionNumber = 0; 
		
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
	
	private void initializeViews() {
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setOnPageChangeListener(this);
		backBtn = (Button) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		nextBtn = (Button) findViewById(R.id.nextBtn);
		nextBtn.setOnClickListener(this);
	}
	
	private void updateViews() {
		QuizAdapter adapter = new QuizAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		updateButtons();
	}
	
	private void updateButtons() {
		backBtn.setVisibility(questionNumber == 0 ? View.INVISIBLE : View.VISIBLE);
		nextBtn.setText((questionNumber == questions.size()-1) ? R.string.results : R.string.next_q);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			previousQuestion();
			break;
		case R.id.nextBtn:
			nextQuestion();
			break;
		}
	}
	
	private void previousQuestion() {
		if (questionNumber == 0) {	
			return;
		}
		viewPager.setCurrentItem(viewPager.getCurrentItem()-1, true);
	}
	
	private void nextQuestion() {
		if (questionNumber == questions.size()-1) {
			startResultsScreen();
			return;
		}
		viewPager.setCurrentItem(viewPager.getCurrentItem()+1, true);
	}
	
	public class QuizAdapter extends FragmentStatePagerAdapter {
		
		public QuizAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public int getCount() {
			return questions.size();
		}
		
		@Override
		public Fragment getItem(int position) {
			Fragment f = new QuestionFragment();
			Bundle args = new Bundle();
			args.putInt(QuestionFragment.EXTRA_QUESTION_NUMBER, position+1);
			args.putSerializable(QuestionFragment.EXTRA_QUESTION, questions.get(position));
			f.setArguments(args);
			return f;
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int state) {}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

	@Override
	public void onPageSelected(int position) {
		if (position < questionNumber) {
			questionNumber--;
			updateButtons();
		} else {
			questionNumber++;
			updateButtons();
		}
	}
	
	private void startResultsScreen() {
		Intent intent = new Intent(this, ResultsScreen.class);
		startActivity(intent);
		finish();
	}

}
