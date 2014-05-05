package com.iscoreapp.dsels.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.text.TextUtils;
import android.util.Xml;

import com.iscoreapp.dsels.model.Question;

public class QuizDataParser extends ApiParser {
	
	private String key;

	@Override
	public void parse(InputStream is) 
		throws XmlPullParserException, IOException 
	{
		List<Question> questions = new ArrayList<Question>();
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(is, "utf-8");
			parser.next();
			
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					String tagName = parser.getName();
					if (TAG_DICT.equalsIgnoreCase(tagName)) {
						Question question = readQuestion(parser);
						questions.add(question);
					}
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
		apiResponse.setData(questions);
	}
	
	private Question readQuestion(XmlPullParser parser)
		throws XmlPullParserException, IOException
	{
		Question question = new Question();
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
			String tagName = parser.getName();
			if (TAG_KEY.equalsIgnoreCase(tagName)) {
				key = readString(parser, TAG_KEY);
			} else if (TAG_STRING.equalsIgnoreCase(tagName)) {
				String value = readString(parser, TAG_STRING);
				setValue(question, value);
			}
		}
		return question;
	}
	
	private void setValue(Question question, String value) {
		if (question == null) {
			return;
		}
		if (TAG_INSTRUCTION.equalsIgnoreCase(key)) {
			question.setInstructions(value);
		} else if (TAG_QUESTION.equalsIgnoreCase(key)) {
			question.setQuestion(value);
		} else if (TAG_WEIGHT.equalsIgnoreCase(key)) {
			question.setWeight(Integer.parseInt(value));
		} else if (TAG_ANSWER.equalsIgnoreCase(key)) {
			question.setAnswer(value);
		} else if (TAG_CHOICE1.equalsIgnoreCase(key)) {
			question.setChoice1(value);
		} else if (TAG_CHOICE2.equalsIgnoreCase(key)) {
			question.setChoice2(value);
		} else if (TAG_CHOICE3.equalsIgnoreCase(key)) {
			question.setChoice3(value);
		} else if (TAG_CHOICE4.equalsIgnoreCase(key)) {
			question.setChoice4(value);
		} else if (TAG_USER_INPUT.equalsIgnoreCase(key)) {
			if (!TextUtils.isEmpty(value) && value.equals("TBC")) {
				question.setUserInput(null);
			} else {
				question.setUserInput(value);
			}
		}
	}

}
