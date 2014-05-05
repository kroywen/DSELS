package com.iscoreapp.dsels.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.iscoreapp.dsels.model.QuizListItem;

public class QuizListParser extends ApiParser {

	@Override
	public void parse(InputStream is) 
		throws XmlPullParserException, IOException 
	{
		List<QuizListItem> quizList = new ArrayList<QuizListItem>();
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(is, "utf-8");
			parser.next();
			
			parser.require(XmlPullParser.START_TAG, namespace, TAG_QUIZ_LIST);
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					String tagName = parser.getName();
					if (tagName.equalsIgnoreCase(TAG_QUIZ)) {
						QuizListItem quizItem = readQuizItem(parser);
						quizList.add(quizItem);
					}
				}
				eventType = parser.next();
			}
			parser.require(XmlPullParser.END_TAG, namespace, TAG_QUIZ_LIST);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
		apiResponse.setData(quizList);
	}
	
	private QuizListItem readQuizItem(XmlPullParser parser)
		throws XmlPullParserException, IOException
	{
		QuizListItem quizItem = new QuizListItem();
		parser.require(XmlPullParser.START_TAG, namespace, TAG_QUIZ);
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
			String tagName = parser.getName();
			if (TAG_NAME.equalsIgnoreCase(tagName)) {
				String name = readString(parser, TAG_NAME);
				quizItem.setName(name);
			} else if (TAG_DESCRIPTION.equalsIgnoreCase(tagName)) {
				String description = readString(parser, TAG_DESCRIPTION);
				quizItem.setDescription(description);
			} else if (TAG_INTRO_LOCATION.equalsIgnoreCase(tagName)) {
				String introLocation = readString(parser, TAG_INTRO_LOCATION);
				quizItem.setIntroLocation(introLocation);
			} else if (TAG_DATA_LOCATION.equalsIgnoreCase(tagName)) {
				String dataLocation = readString(parser, TAG_DATA_LOCATION);
				quizItem.setDataLocation(dataLocation);
			} else {
				skip(parser);
			}
		}
		parser.require(XmlPullParser.END_TAG, namespace, TAG_QUIZ);
		return quizItem;
	}

}
