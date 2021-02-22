package com.vaskevicius.android.banker.framework.mvp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.vaskevicius.android.banker.BankerApplication;
import com.vaskevicius.android.banker.framework.di.component.ActivityComponent;
import com.vaskevicius.android.banker.framework.di.component.ApplicationComponent;
import com.vaskevicius.android.banker.framework.di.component.DaggerActivityComponent;
import com.vaskevicius.android.banker.framework.di.module.ActivityModule;
import com.vaskevicius.android.banker.framework.mvp.MvpView;
import com.vaskevicius.android.banker.utils.CommonUtils;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

  private ActivityComponent component;
  private ProgressDialog progressDialog;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    injectComponent(getComponent());
    super.onCreate(savedInstanceState);
  }

  protected abstract void injectComponent(ActivityComponent component);

  ActivityComponent getComponent() {
    if (component == null) {
      component = DaggerActivityComponent.builder()
          .applicationComponent(getApplicationComponent())
          .activityModule(new ActivityModule(this))
          .build();
    }
    return component;
  }

  @Override
  public void showLoading() {
    hideLoading();
    progressDialog = CommonUtils.showLoadingDialog(this);
  }

  @Override
  public void hideLoading() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.cancel();
    }
  }

  public BankerApplication getBankerApplication() {
    return (BankerApplication) getApplication();
  }

  public ApplicationComponent getApplicationComponent() {
    return getBankerApplication().applicationComponent();
  }

  public ActivityComponent getActivityComponent() {
    return component;
  }
}
