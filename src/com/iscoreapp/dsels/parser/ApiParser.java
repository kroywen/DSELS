package com.iscoreapp.dsels.parser;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.iscoreapp.dsels.api.ApiResponse;

public abstract class ApiParser {
	
	protected static final String namespace = null;
	
	public static final String TAG_DICT = "dict";
	public static final String TAG_ARRAY = "array";
	public static final String TAG_KEY = "key";
	public static final String TAG_STRING = "string";
	
	public static final String TAG_QUIZ_LIST = "QuizList";
	public static final String TAG_QUIZ = "Quiz";
	public static final String TAG_NAME = "Name";
	public static final String TAG_DESCRIPTION = "Description";
	public static final String TAG_INTRO_LOCATION = "IntroLocation";
	public static final String TAG_DATA_LOCATION = "DataLocation";
	public static final String TAG_PAPER_TYPE = "PaperType";
	public static final String TAG_PAPER_NAME = "PaperName";
	public static final String TAG_TOTAL_Q_NUMBER = "TotalQNumber";
	public static final String TAG_ALLOWED_TIME = "AllowedTime";
	public static final String TAG_UPDATED = "Updated";
	public static final String TAG_INSTRUCTION = "Instruction";
	public static final String TAG_QUESTION = "Question";
	public static final String TAG_WEIGHT = "Weight";
	public static final String TAG_ANSWER = "Answer";
	public static final String TAG_CHOICE1 = "Choice1";
	public static final String TAG_CHOICE2 = "Choice2";
	public static final String TAG_CHOICE3 = "Choice3";
	public static final String TAG_CHOICE4 = "Choice4";
	public static final String TAG_USER_INPUT = "UserInput";
	
	protected ApiResponse apiResponse;
	
	public ApiParser() {
		apiResponse = new ApiResponse();
	}
	
	public ApiResponse getApiResponse() {
		return apiResponse;
	}
	
	public void setApiResponse(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}
	
	public abstract void parse(InputStream is) 
		throws XmlPullParserException, IOException;
	
	protected String readString(XmlPullParser parser, String tagName)
		throws XmlPullParserException, IOException
	{
		parser.require(XmlPullParser.START_TAG, namespace, tagName);
		String str = readText(parser);
		parser.require(XmlPullParser.END_TAG, namespace, tagName);
		return str;
	}
	
	protected int readInt(XmlPullParser parser, String tagName)
		throws XmlPullParserException, IOException
	{
		parser.require(XmlPullParser.START_TAG, namespace, tagName);
		int number = Integer.parseInt(readText(parser));
		parser.require(XmlPullParser.END_TAG, namespace, tagName);
		return number;
	}
	
	protected String readText(XmlPullParser parser) 
		throws XmlPullParserException, IOException
	{
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}
	
	protected void skip(XmlPullParser parser) 
		throws XmlPullParserException, IOException 
	{
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }

}
