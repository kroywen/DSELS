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
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.fragment.QuestionFragment;
import com.iscoreapp.dsels.model.Question;
import com.iscoreapp.dsels.model.Quiz;

public class QuestionScreen extends BaseScreen implements OnClickListener, OnPageChangeListener {
	
	private String name;
	private int questionNumber;
	private Quiz quiz;
	private List<Question> questions;
	
	private ViewPager viewPager;
	private Button backBtn;
	private Button nextBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_screen);
		initializeViews();
		
		ActionBar actionbar = getActionBar();
		if (actionbar != null) {
			actionbar.setDisplayHomeAsUpEnabled(true);
		}
		
		getIntentData();
		if (TextUtils.isEmpty(name)) {
			finish();
			return;
		}
		
		quiz = quizProvider.loadQuiz(name);
		questions = quiz.getQuestions();
		
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
			questionNumber = intent.getIntExtra(EXTRA_QUESTION_NUMBER, 0);
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
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		// TODO Auto-generated method stub
		
	}

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

}
