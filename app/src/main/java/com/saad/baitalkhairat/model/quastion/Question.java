package com.saad.baitalkhairat.model.quastion;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {

    @SerializedName("id")
    int id;
    @SerializedName("question")
    String question;
    @SerializedName("answer")
    String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
