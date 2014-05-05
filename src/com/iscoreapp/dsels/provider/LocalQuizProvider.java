package com.iscoreapp.dsels.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import android.content.Context;

import com.iscoreapp.dsels.model.Question;
import com.iscoreapp.dsels.model.Quiz;
import com.iscoreapp.dsels.model.QuizListItem;

public class LocalQuizProvider {
	
	public static final String QUIZ_LIST_FILENAME = "quiz_list";
	
	private Context context;
	
	public LocalQuizProvider(Context context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public List<QuizListItem> loadQuizList() {
		try {
			String filename = context.getCacheDir() + QUIZ_LIST_FILENAME;
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<QuizListItem> quizList = (List<QuizListItem>) ois.readObject();
			ois.close();
			fis.close();
			return quizList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void storeQuizList(List<QuizListItem> quizList) {
		try {
			String filename = context.getCacheDir() + File.separator + QUIZ_LIST_FILENAME;
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(quizList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public Quiz loadQuizIntro(String name) {
		try {
			String filename = context.getCacheDir() + File.separator + name + "Intro";
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Quiz quiz = (Quiz) ois.readObject();
			ois.close();
			fis.close();
			return quiz;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void storeQuizIntro(String name, Quiz quiz) {
		try {
			String filename = context.getCacheDir() + File.separator + quiz.getName() + "Intro";
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(quiz);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@SuppressWarnings("unchecked")
	public List<Question> loadQuizData(String name) {
		try {
			String filename = context.getCacheDir() + File.separator + name;
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<Question> questions = (List<Question>) ois.readObject();
			ois.close();
			fis.close();
			return questions;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void storeQuizData(String name, List<Question> questions) {
		try {
			String filename = context.getCacheDir() + File.separator + name;
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(questions);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
