package com.vaskevicius.android.banker.ui.main;

import com.vaskevicius.android.banker.data.models.Transaction;
import com.vaskevicius.android.banker.framework.mvp.MvpView;
import java.util.List;

public interface MainMvpView extends MvpView {

  void update(List<Transaction> transactions);

  void showError();

}
