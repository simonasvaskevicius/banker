package com.vaskevicius.android.banker.data.network;

import com.vaskevicius.android.banker.data.models.Transaction;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Retrofit;

public class ApiHelper implements IApiHelper {

  private Retrofit retrofit;

  @Inject
  public ApiHelper(Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  @Override
  public Observable<List<Transaction>> getTransactions() {
    return retrofit.create(NetworkClient.class).getTransactions();
  }
}
