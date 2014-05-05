package com.iscoreapp.dsels.parser;

import com.iscoreapp.dsels.api.ApiData;

import android.text.TextUtils;

public class ParserFactory {
	
	public static ApiParser getParser(String command) {
		if (TextUtils.isEmpty(command)) {
			return null;
		} else if (ApiData.COMMAND_QUIZ_LIST.equalsIgnoreCase(command)) {
			return new QuizListParser();
		} else if (ApiData.COMMAND_QUIZ_INTRO.equalsIgnoreCase(command)) {
			return new QuizIntroParser();
		} else if (ApiData.COMMAND_QUIZ_DATA.equalsIgnoreCase(command)) {
			return new QuizDataParser();
		} else {
			return null;
		}
	}

}
