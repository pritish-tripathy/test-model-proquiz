package com.quizpro.model;

import javax.persistence.*;

@Entity
@Table(name = "myquestion_options")
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    private String optionData;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public QuestionOption() {
    }

    public QuestionOption(Long optionId, String optionData, Question question) {
        this.optionId = optionId;
        this.optionData = optionData;
        this.question = question;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionData() {
        return optionData;
    }

    public void setOptionData(String optionData) {
        this.optionData = optionData;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
