package com.iscoreapp.dsels.screen;

import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.iscoreapp.dsels.R;
import com.iscoreapp.dsels.api.ApiResponse;
import com.iscoreapp.dsels.api.ApiResponseReceiver;
import com.iscoreapp.dsels.api.ApiService;
import com.iscoreapp.dsels.api.OnApiResponseListener;
import com.iscoreapp.dsels.model.Question;
import com.iscoreapp.dsels.model.Quiz;
import com.iscoreapp.dsels.provider.LocalQuizProvider;
import com.iscoreapp.dsels.provider.RemoteQuizProvider;

public class BaseScreen extends FragmentActivity implements OnApiResponseListener {
	
	public static final String EXTRA_QUIZ_NAME = "extra_quiz_name";
	public static final String EXTRA_INTRO_LOCATION = "extra_intro_location";
	public static final String EXTRA_DATA_LOCATION = "extra_data_location";
	
	protected ApiResponseReceiver responseReceiver;
	private ProgressDialog progress;
	protected static Quiz quiz;
	protected static List<Question> questions;
	
	protected LocalQuizProvider localQuizProvider;
	protected RemoteQuizProvider remoteQuizProvider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setTitle(R.string.app_title);
		}
		
		localQuizProvider = new LocalQuizProvider(this);
		remoteQuizProvider = new RemoteQuizProvider(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter(ApiService.ACTION_API_RESULT);
		responseReceiver = new ApiResponseReceiver(this);
		LocalBroadcastManager.getInstance(this).registerReceiver(
			responseReceiver, intentFilter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(responseReceiver);
	}
	
	protected boolean isConnectionAvailable() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return info != null && info.isConnected();
	}

	@Override
	public void onApiResponse(int apiStatus, ApiResponse apiResponse) {
		// TODO Auto-generated method stub
		
	}
	
	protected void showProgressDialog(String message) {
		if (progress == null) {
			progress = new ProgressDialog(this);
			if (!TextUtils.isEmpty(message)) {
				progress.setMessage(message);
			}
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setCancelable(false);
		}
		if (!progress.isShowing()) {
			progress.show();
		}
	}
	
	protected void hideProgressDialog() {
		if (progress != null) {
			if (progress.isShowing()) {
				progress.dismiss();
			}
		}
	}
	
	protected void showDialog(int titleId, int messageId, 
		DialogInterface.OnClickListener okListener, DialogInterface.OnCancelListener cancelListener) 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titleId)
			.setMessage(messageId)
			.setPositiveButton(android.R.string.ok, okListener)
			.setOnCancelListener(cancelListener)
			.create()
			.show();
	}

}
