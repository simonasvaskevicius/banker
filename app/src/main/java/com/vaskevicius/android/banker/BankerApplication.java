package com.vaskevicius.android.banker;

import android.app.Application;
import com.vaskevicius.android.banker.framework.di.component.ApplicationComponent;
import com.vaskevicius.android.banker.framework.di.component.DaggerApplicationComponent;
import com.vaskevicius.android.banker.framework.di.module.ApplicationModule;

public class BankerApplication extends Application {

  private ApplicationComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
      component = createApplicationComponent();
      component.inject(this);
  }

  protected ApplicationComponent createApplicationComponent() {
    return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public void setApplicationComponent(ApplicationComponent component) {
    this.component = component;
  }

  public ApplicationComponent applicationComponent() {
    return component;
  }
}
