package com.vaskevicius.android.banker.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.vaskevicius.android.banker.databinding.ViewSummaryBinding;
import com.vaskevicius.android.banker.utils.FormattingUtils;

public class SummaryView extends LinearLayout implements OnClickListener {

  ViewSummaryBinding binding;

  private final int SPENDING = 1;
  private final int RECEIVING = 2;

  private int currentSide = SPENDING;

  public SummaryView(Context context) {
    super(context);
    init();
  }

  public SummaryView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public SummaryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public SummaryView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {
    binding = ViewSummaryBinding.inflate(LayoutInflater.from(getContext()), this, true);
    binding.container.setOnClickListener(this);
  }

  public void setTitle(CharSequence title) {
    binding.title.setText(title);
  }

  public void setSpent(double amount) {
    binding.spent.setText(FormattingUtils.getFormattedAmount(amount));
  }

  public void setReceived(double amount) {
    binding.received.setText(FormattingUtils.getFormattedAmount(amount));
  }

  public void setMostSpent(String text) {
    binding.mostSpent.setVisibility(text == null? GONE: VISIBLE);
    if (text != null) binding.mostSpent.setText(text);
  }

  public void setMostReceived(String text) {
    binding.mostReceived.setVisibility(text == null? GONE: VISIBLE);
    if (text != null) binding.mostReceived.setText(text);
  }


  public void change() {
    currentSide = currentSide == SPENDING ? RECEIVING : SPENDING;
    boolean isSpendingVisible = currentSide == SPENDING;
    binding.spendLayout.setVisibility(isSpendingVisible ? VISIBLE : GONE);
    binding.receiveLayout.setVisibility(isSpendingVisible ? GONE : VISIBLE);
  }

  @Override
  public void onClick(View v) {
    change();
  }
}
