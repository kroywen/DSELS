package com.iscoreapp.dsels.provider;

import android.content.Context;
import android.content.Intent;

import com.iscoreapp.dsels.api.ApiData;
import com.iscoreapp.dsels.api.ApiService;

public class RemoteQuizProvider {
	
	private Context context;
	
	public RemoteQuizProvider(Context context) {
		this.context = context;
	}

	public void loadQuizList() {
		Intent intent = new Intent(context, ApiService.class);
		intent.setAction(ApiData.COMMAND_QUIZ_LIST);
		context.startService(intent);
	}

	public void loadQuizIntro(String url) {
		Intent intent = new Intent(context, ApiService.class);
		intent.setAction(ApiData.COMMAND_QUIZ_INTRO);
		intent.putExtra(ApiData.URL_QUIZ_INTRO, url);
		context.startService(intent);
	}

	public void loadQuizData(String url) {
		Intent intent = new Intent(context, ApiService.class);
		intent.setAction(ApiData.COMMAND_QUIZ_DATA);
		intent.putExtra(ApiData.URL_QUIZ_DATA, url);
		context.startService(intent);
	}

}
