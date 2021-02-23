package com.vaskevicius.android.banker.ui.main;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.snackbar.Snackbar;
import com.vaskevicius.android.banker.R;
import com.vaskevicius.android.banker.data.models.Summary;
import com.vaskevicius.android.banker.data.models.Transaction;
import com.vaskevicius.android.banker.data.models.TransactionType;
import com.vaskevicius.android.banker.databinding.ActivityMainBinding;
import com.vaskevicius.android.banker.framework.di.component.ActivityComponent;
import com.vaskevicius.android.banker.framework.mvp.base.BaseActivity;
import com.vaskevicius.android.banker.utils.DateUtils;
import com.vaskevicius.android.banker.utils.FormattingUtils;
import com.vaskevicius.android.banker.utils.SummaryUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvpView {

  @Inject
  MainMvpPresenter<MainMvpView> presenter;

  TransactionAdapter adapter;

  ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    presenter.onAttach(this);
    setupComponents();
    presenter.onViewPrepared();
  }

  @Override
  public void update(List<Transaction> transactions) {
    binding.swipeRefresh.setRefreshing(false);
    updateUI(transactions);
  }

  private void setupComponents() {
    binding.swipeRefresh.setOnRefreshListener(() -> presenter.onViewPrepared());
    binding.balance.setTypeface(getResources().getFont(R.font.opensans_light));
    setupRecyclerView();
    setupBackToTop();
  }

  /**
   * Data update
   */

  private void updateUI(List<Transaction> transactions) {
    //Sorts list by date descending
    setBalance(transactions);

    Collections.sort(transactions);

    setupSummaryView(transactions);

    adapter.setItems(transactions);
    binding.recyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }

  /**
   * UI Setup
   */

  private void setupBackToTop() {
    binding.nestedScrollView.setOnScrollChangeListener(
        (OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> binding.fab
            .setVisibility(scrollY > 1000 ? View.VISIBLE : View.GONE));

    binding.fab.setOnClickListener(v -> binding.nestedScrollView.smoothScrollTo(0, 0));
  }

  private void setupRecyclerView() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    adapter = new TransactionAdapter(this);

    binding.recyclerView.setHasFixedSize(true);
    binding.recyclerView.setLayoutManager(layoutManager);
  }

  private void setupSummaryView(List<Transaction> transactions) {
    Date today = DateUtils.getTodaysDate();

    Transaction mostSpentOn = SummaryUtils
        .getMostTransaction(transactions, today,
            TransactionType.TYPE_DEBIT);

    Transaction mostReceivedFrom = SummaryUtils
        .getMostTransaction(transactions, today,
            TransactionType.TYPE_CREDIT);

    boolean hasSummary = mostSpentOn != null && mostReceivedFrom != null;

    //If there is no transactions this month, SummaryView will not be visible
    binding.summaryView.setVisibility(hasSummary ? View.VISIBLE : View.GONE);
    if (hasSummary) {
      binding.summaryView.setSpent(
          SummaryUtils
              .getSummaryOnMonthByType(transactions, today, TransactionType.TYPE_DEBIT)
              .getAmount().doubleValue());

      binding.summaryView.setReceived(
          SummaryUtils.getSummaryOnMonthByType(transactions, today,
              TransactionType.TYPE_CREDIT).getAmount().doubleValue());

      binding.summaryView.setMostSpent(
          FormattingUtils.getFormattedItemPlusPrice(mostSpentOn.getDescription(),
              FormattingUtils.getFormattedAmount(mostSpentOn.getAmount())));

      binding.summaryView.setMostReceived(FormattingUtils
          .getFormattedItemPlusPrice(mostReceivedFrom.getDescription(),
              FormattingUtils.getFormattedAmount(mostReceivedFrom.getAmount())));
    }
  }

  private void setBalance(List<Transaction> transactions) {
    binding.balance.setCharacterLists(FormattingUtils.getFormattedAmount(SummaryUtils.getBalance(transactions)));
    binding.balance.setText(FormattingUtils.getFormattedAmount(SummaryUtils.getBalance(transactions)));
  }

  @Override
  public void showError() {
    Snackbar.make(binding.container, getString(R.string.network_error), Snackbar.LENGTH_SHORT)
        .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show();
  }

  @Override
  protected void onDestroy() {
    presenter.onDetach();
    super.onDestroy();
  }

  @Override
  protected void injectComponent(ActivityComponent component) {
    component.inject(this);
  }
}