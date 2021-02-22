package com.vaskevicius.android.banker.framework.di.component;

import com.vaskevicius.android.banker.framework.di.module.ActivityModule;
import com.vaskevicius.android.banker.framework.di.module.NetworkModule;
import com.vaskevicius.android.banker.framework.di.scope.PerActivity;
import com.vaskevicius.android.banker.ui.main.MainActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity activity);

}
