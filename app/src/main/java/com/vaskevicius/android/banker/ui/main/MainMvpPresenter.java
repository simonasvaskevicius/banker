package com.vaskevicius.android.banker.ui.main;

import com.vaskevicius.android.banker.framework.mvp.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

  void onViewPrepared();

}
