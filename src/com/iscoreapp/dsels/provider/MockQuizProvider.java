package com.iscoreapp.dsels.provider;

import com.iscoreapp.dsels.model.Question;
import com.iscoreapp.dsels.model.Quiz;

public class MockQuizProvider extends QuizProvider {

	@Override
	public String[] loadtQuizNameList() {
		return new String[] {"SampleData1", "SampleData2", "SampleData3", "SampleData4", "SampleData5"};
	}

	@Override
	public Quiz loadQuiz(String name) {
		Quiz quiz = new Quiz();
		quiz.setName("SampleData1");
		quiz.setType("Energy and Environment");
		quiz.setQuestionCount(8);
		quiz.setAllowedTime("10 minutes");
		quiz.setUpdated("2013-11-30T15:45HKT");
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"Which one/s of the following is/are the type/s of waste in Hong Kong?",
			1,
			"Construction waste",
			"Livestock waste",
			"All of the above",
			"All of the above",
			"D",
			null
		));
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"Primary energy can be classified into:",
			1,
			"original energy, modern energy",
			"non-renewable energy, renewable energy",
			"original energy, renewable energy",
			"non-renewable energy, modern energy",
			"B",
			null
		));
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"In March, 2011, earthquake and tsunami knocked out cooling systems to reactors in Fukushima, Japan, which led to meltdowns and the release of radioactivity. What did we learn from this accident?",
			1,
			"Nuclear plants may not be safe when there is a natural disaster.",
			"Nuclear accidents could cause large-scale pollutions to the environment.",
			"Man-made mistakes were possible causes of nuclear accidents.",
			"All of the above.",
			"D",
			null
		));
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"Non-renewable energy:",
			1,
			"has unlimited supply in the world.",
			"is a new source of energy.",
			"has limited supply in the world.",
			"is likely to be produced in a short period.",
			"C",
			null
		));
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"Greenhouse gases:",
			1,
			"are the gases which plants emit in a greenhouse.",
			"are carbon dioxide, methane, nitrous oxide and fluorinated gases.",
			"do not contribute to global warming.",
			"have decreased drastically in emission since the 1970’s.",
			"B",
			null
		));
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"How can we save energy?",
			1,
			"On our walls at home, we can use wall papers or paint of deeper colours.",
			"We can set the indoor temperature from 21 to 25 in summer.",
			"We can keep our lighting appliances clean regularly.",
			"None of the above.",
			"C",
			null
		));
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"Which one of the following is a group of new and renewable energy?",
			1,
			"Hydro energy, solar energy, coal.",
			"Energy from waste, gasoline, tidal energy.",
			"Geothermal energy, natural gas, wind energy.",
			"Solar energy, wind energy, wave energy.",
			"D",
			null
		));
		quiz.addQuestion(new Question(
			"Please choose an appropriate answer.",
			"Which one of the following is appropriate for the energy situation in Hong Kong?",
			1,
			"Solar and wind energy are found.",
			"Hong Kong is rich in energy source.",
			"Renewable energy is prevalent.",
			"Energy is mainly exported.",
			"A",
			null
		));
		return quiz;
	}

}
