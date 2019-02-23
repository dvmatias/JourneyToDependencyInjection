package com.matias.journeytodependencyinjection.model.networking;

import com.google.gson.annotations.SerializedName;
import com.matias.journeytodependencyinjection.model.ui.QuestionDetails;

import java.util.Collections;
import java.util.List;

public class SingleQuestionResponseSchema {

    @SerializedName("items")
    private final List<QuestionDetails> mQuestions;

    public SingleQuestionResponseSchema(QuestionDetails question) {
        mQuestions = Collections.singletonList(question);
    }

    public QuestionDetails getQuestion() {
        return mQuestions.get(0);
    }
}

