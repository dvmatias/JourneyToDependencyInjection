package com.matias.journeytodependencyinjection.common.mvp;

/**
 * Presenter interface.
 *
 * @param <V>
 */
public interface BasePresenter<V extends BaseView> {

    void bind(V view);

    void unbind();

    boolean isBound();
}
