package com.matias.journeytodependencyinjection.common.dependencyinjection;

import com.matias.journeytodependencyinjection.common.ImageLoader;
import com.matias.journeytodependencyinjection.screens.common.DialogsManager;
import com.matias.journeytodependencyinjection.screens.questiondetails.QuestionDetailsPresenterImpl;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsAdapter;
import com.matias.journeytodependencyinjection.screens.questionlist.QuestionsListPresenterImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector {

    private PresentationCompositionRoot presentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        this.presentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client) {
        Class clazz = client.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field);
            }
        }
    }

    private boolean isAnnotatedForInjection(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof Service){
                return true;
            }
        }

        return false;
    }

    private void injectField(Object client, Field field) {
        try {
            boolean isAccessibleInitially = field.isAccessible();
            field.setAccessible(true);
            field.set(client, getServiceForClass(field.getType()));
            field.setAccessible(isAccessibleInitially);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getServiceForClass(Class<?> type) {
        if (type.equals(DialogsManager.class)) {
            return presentationCompositionRoot.getDialogsManager();
        } else if (type.equals(QuestionsAdapter.class)) {
            return presentationCompositionRoot.getQuestionsAdapter();
        } else if (type.equals(QuestionsListPresenterImpl.class)) {
            return presentationCompositionRoot.getQuestionsListPresenterImpl();
        } else if (type.equals(QuestionDetailsPresenterImpl.class)) {
            return presentationCompositionRoot.getQuestionDetailsPresenterImpl();
        } else if (type.equals(ImageLoader.class)) {
            return presentationCompositionRoot.getImageLoader();
        }
        else {
            throw new RuntimeException("unsupported service type class: " + type);
        }
    }
}
