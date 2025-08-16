package com.quizpro.model;

import javax.persistence.*;

@Entity
@Table(name = "practice_test_question_results")
public class PracticeTestQuestionResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "practice_test_id")
	private PracticeTestResult practiceTestResult;

	private Long questionId;

	@Lob
	private String question;

	private String correctAnswer;
	private String yourAnswer;
	private String status; // "CORRECT" or "INCORRECT"

	public Long getId() {
		return id;
	}

	public PracticeTestResult getPracticeTestResult() {
		return practiceTestResult;
	}

	public void setPracticeTestResult(PracticeTestResult practiceTestResult) {
		this.practiceTestResult = practiceTestResult;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getYourAnswer() {
		return yourAnswer;
	}

	public void setYourAnswer(String yourAnswer) {
		this.yourAnswer = yourAnswer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
