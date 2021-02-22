package com.vaskevicius.android.banker.framework.di.component;

import android.app.Application;
import android.content.Context;
import com.vaskevicius.android.banker.BankerApplication;
import com.vaskevicius.android.banker.framework.di.module.ApplicationModule;
import com.vaskevicius.android.banker.framework.di.module.NetworkModule;
import com.vaskevicius.android.banker.framework.di.scope.ApplicationContext;
import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

  void inject(BankerApplication application);

  @ApplicationContext
  Context context();

  Application application();

  Retrofit retrofit();

}
