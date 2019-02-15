package com.matias.journeytodependencyinjection.networking;

import com.google.gson.annotations.SerializedName;
import com.matias.journeytodependencyinjection.questions.Question;

import java.util.List;

public class QuestionsListResponseSchema {

    @SerializedName("items")
    private final List<Question> mQuestions;

    /**
     * Constructor.
     *
     * @param questions List<Question> List of Question objects.
     */
    public QuestionsListResponseSchema(List<Question> questions) {
        mQuestions = questions;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }
}
