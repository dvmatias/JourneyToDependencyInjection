package com.matias.journeytodependencyinjection.common.mvp;

/**
 * Each presenter implementation should extend this class.
 *
 * @param <V>
 */
public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    /**
     * MVP - View.
     */
    public V view = null;

    @Override
    public void bind(V view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
    }

    @Override
    public boolean isBound() {
        return view != null;
    }
}
