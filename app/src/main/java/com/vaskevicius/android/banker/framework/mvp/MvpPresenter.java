package com.vaskevicius.android.banker.framework.mvp;

public interface MvpPresenter<V extends MvpView> {

  void onAttach(V view);

  void onDetach();
}
