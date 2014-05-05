package com.iscoreapp.dsels.parser;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.iscoreapp.dsels.model.Quiz;

public class QuizIntroParser extends ApiParser {
	
	private String key;

	@Override
	public void parse(InputStream is) 
		throws XmlPullParserException, IOException 
	{
		Quiz quiz = new Quiz();
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(is, "utf-8");
			parser.next();
			
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					String tagName = parser.getName();
					if (TAG_KEY.equalsIgnoreCase(tagName)) {
						key = readString(parser, TAG_KEY);
					} else if (TAG_STRING.equalsIgnoreCase(tagName)) {
						String value = readString(parser, TAG_STRING);
						setValue(quiz, value);	
					}
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
		apiResponse.setData(quiz);
	}
	
	private void setValue(Quiz quiz, String value) {
		if (quiz == null) {
			return;
		}
		if (TAG_PAPER_NAME.equalsIgnoreCase(key)) {
			quiz.setName(value);
		} else if (TAG_PAPER_TYPE.equalsIgnoreCase(key)) {
			quiz.setType(value);
		} else if (TAG_TOTAL_Q_NUMBER.equalsIgnoreCase(key)) {
			quiz.setQuestionCount(Integer.parseInt(value));
		} else if (TAG_ALLOWED_TIME.equalsIgnoreCase(key)) {
			quiz.setAllowedTime(value);
		} else if (TAG_UPDATED.equalsIgnoreCase(key)) {
			quiz.setUpdated(value);
		}
		key = null;
	}

}
