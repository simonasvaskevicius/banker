package com.vaskevicius.android.banker.framework.mvp.base;

import com.vaskevicius.android.banker.framework.mvp.MvpPresenter;
import com.vaskevicius.android.banker.framework.mvp.MvpView;
import javax.inject.Inject;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

  private V view;

  @Inject
  public BasePresenter() {
  }

  @Override
  public void onAttach(V view) {
    this.view = view;
  }

  @Override
  public void onDetach() {
    view = null;
  }

  public boolean isViewAttached() {
    return view != null;
  }

  public V getView() {
    return view;
  }
}
