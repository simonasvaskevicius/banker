package com.vaskevicius.android.banker.ui.main;

import android.annotation.SuppressLint;
import com.vaskevicius.android.banker.data.models.Transaction;
import com.vaskevicius.android.banker.data.network.ApiHelper;
import com.vaskevicius.android.banker.framework.mvp.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements
    MainMvpPresenter<V> {

  private ApiHelper apiHelper;

  @Inject
  public MainPresenter(ApiHelper apiHelper){
    this.apiHelper = apiHelper;
  }

  @Override
  public void onViewPrepared() {
    fetchTransactions();
  }

  @SuppressLint("CheckResult")
  private void fetchTransactions() {
    getView().showLoading();
    apiHelper.getTransactions().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(
        new DisposableObserver<List<Transaction>>() {
          @Override
          public void onNext(@NonNull List<Transaction> transactions) {
            if(isViewAttached()){
              if(!transactions.isEmpty()) {
                getView().update(transactions);
              }
            }
          }

          @Override
          public void onError(@NonNull Throwable e) {
            getView().showError();
          }

          @Override
          public void onComplete() {
            getView().hideLoading();
          }
        });
  }
}
