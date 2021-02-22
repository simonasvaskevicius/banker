package com.vaskevicius.android.banker.data.network;

import com.vaskevicius.android.banker.data.models.Transaction;
import io.reactivex.Observable;
import java.util.List;

public interface IApiHelper {

  Observable<List<Transaction>> getTransactions();

}
