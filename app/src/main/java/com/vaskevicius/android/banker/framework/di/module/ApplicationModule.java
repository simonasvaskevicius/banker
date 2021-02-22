package com.vaskevicius.android.banker.framework.di.module;

import android.app.Application;
import android.content.Context;
import com.vaskevicius.android.banker.BuildConfig;
import com.vaskevicius.android.banker.data.network.ApiHelper;
import com.vaskevicius.android.banker.framework.di.scope.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

  //Application level instances

  private final Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @ApplicationContext
  Context provideContext() {
    return application;
  }

  @Provides
  Application provideApplication() {
    return application;
  }

  @Provides
  @Singleton
  ApiHelper provideApiHelper(Retrofit retrofit) {
    return new ApiHelper(retrofit);
  }

}
