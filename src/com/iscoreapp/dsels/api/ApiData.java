package com.iscoreapp.dsels.api;

import android.os.Bundle;

public class ApiData {
	
	public static final String BASE_URL = "http://android.iscoreapp.com/quizes/";
	
	public static final String COMMAND_QUIZ_LIST = "QuizList";
	public static final String COMMAND_QUIZ_INTRO = "QuizIntro";
	public static final String COMMAND_QUIZ_DATA = "QuizData";
	
	public static final String URL_QUIZ_INTRO = "url_quiz_intro";
	public static final String URL_QUIZ_DATA = "url_quiz_data";
		
	public static String createURL(String command, Bundle params) {
		if (COMMAND_QUIZ_LIST.equals(command)) {
			return BASE_URL + "quiz_list.xml";
		} else if (COMMAND_QUIZ_INTRO.equals(command)) {
			return params.getString(URL_QUIZ_INTRO);
		} else if (COMMAND_QUIZ_DATA.equals(command)) {
			return params.getString(URL_QUIZ_DATA);
		} else {
			return null;
		}
	}

}
