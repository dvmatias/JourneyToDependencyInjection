package com.matias.journeytodependencyinjection.common.dependencyinjection;

import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListActivity;

public class Injector {

    private PresentationCompositionRoot presentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        this.presentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client) {
        if (client instanceof QuestionsListActivity) {
            injectQuestionsListActivity((QuestionsListActivity) client);
        } else if (client instanceof QuestionDetailsActivity) {
            injectQuestionDetailsActivity((QuestionDetailsActivity) client);
        } else {
            throw new RuntimeException("INVALID CLIENT: " + client);
        }
    }

    private void injectQuestionsListActivity(QuestionsListActivity client) {
        client.questionsAdapter = presentationCompositionRoot.getQuestionsAdapter();
        client.presenter = presentationCompositionRoot.getQuestionsListPresenterImpl();
        client.dialogsManager = presentationCompositionRoot.getDialogsManager();
    }

    private void injectQuestionDetailsActivity(QuestionDetailsActivity client) {
        client.presenter = presentationCompositionRoot.getQuestionDetailsPresenterImpl();
        client.dialogsManager = presentationCompositionRoot.getDialogsManager();
        client.imageLoader = presentationCompositionRoot.getImageLoader();
    }
}
