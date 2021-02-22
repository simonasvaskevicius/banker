package com.vaskevicius.android.banker.framework.di.module;

import android.app.Activity;
import android.content.Context;
import com.vaskevicius.android.banker.framework.di.scope.ActivityContext;
import com.vaskevicius.android.banker.ui.main.MainMvpPresenter;
import com.vaskevicius.android.banker.ui.main.MainMvpView;
import com.vaskevicius.android.banker.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

  //Activity level instances

  private Activity activity;

  public ActivityModule(Activity activity){this.activity = activity;}

  @Provides
  @ActivityContext
  Context provideContext() {return activity;}

  @Provides
  Activity provideActivity() {return activity;}

  @Provides
  MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> mainPresenter) {
    return mainPresenter;
  }

}
