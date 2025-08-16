package com.quizpro.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "practice_test_results")
public class PracticeTestResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long practiceTestId;

	private Long studentId;
	private String studentName;

	private String courseName;
	private String topicName;

	private Integer noOfQuestions;
	private Integer marksObtained;

	@OneToMany(mappedBy = "practiceTestResult", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PracticeTestQuestionResult> questionWiseResult = new ArrayList<>();

	public Long getPracticeTestId() {
		return practiceTestId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Integer getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(Integer noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public Integer getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(Integer marksObtained) {
		this.marksObtained = marksObtained;
	}

	public List<PracticeTestQuestionResult> getQuestionWiseResult() {
		return questionWiseResult;
	}

	public void setQuestionWiseResult(List<PracticeTestQuestionResult> questionWiseResult) {
		this.questionWiseResult = questionWiseResult;
	}
}
