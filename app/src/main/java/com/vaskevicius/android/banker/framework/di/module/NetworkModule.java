package com.vaskevicius.android.banker.framework.di.module;

import com.vaskevicius.android.banker.BuildConfig;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

  //Application level network instances
  //Instances created once in application lifetime

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    return new OkHttpClient.Builder().build();
  }

  @Provides
  @Singleton
  GsonConverterFactory provideGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient,
      RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(
        gsonConverterFactory).addCallAdapterFactory(rxJava2CallAdapterFactory).client(okHttpClient)
        .build();
  }
}
