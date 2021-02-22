package com.vaskevicius.android.banker.data.network;

import com.vaskevicius.android.banker.data.models.Transaction;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface NetworkClient {

//  //db with 2020 transactions https://sheet.best/api/sheets/ebb5bfdc-efda-4966-9ecf-d2c171d6985a
//  //db with 2021 and same day transactions https://run.mocky.io/v3/6d479fdc-8fb5-48a0-9e59-b5b726f9f25d
//  When changing @GET link you should also change BASE_URL in module level build.gradle file.
//
//  @GET("/v3/6d479fdc-8fb5-48a0-9e59-b5b726f9f25d")
//  Observable<List<Transaction>> getTransactions();

    @GET("/api/sheets/ebb5bfdc-efda-4966-9ecf-d2c171d6985a")
  Observable<List<Transaction>> getTransactions();
}
