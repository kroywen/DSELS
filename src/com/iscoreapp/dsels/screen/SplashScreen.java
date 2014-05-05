package com.iscoreapp.dsels.screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.iscoreapp.dsels.R;

public class SplashScreen extends BaseScreen {
	
	public static final int DELAY_TIME = 2000;
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		handler.postDelayed(startMainScreenRunnable, DELAY_TIME);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		handler.removeCallbacks(startMainScreenRunnable);
	}
	
	private Runnable startMainScreenRunnable = new Runnable() {
		@Override
		public void run() {
			Intent intent = new Intent(SplashScreen.this, MainScreen.class);
			startActivity(intent);
			finish();
		}
	};

}
