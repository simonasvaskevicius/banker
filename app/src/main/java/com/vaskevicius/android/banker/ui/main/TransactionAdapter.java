package com.vaskevicius.android.banker.ui.main;



import static com.vaskevicius.android.banker.utils.SummaryUtils.getTransactionsWithDates;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.vaskevicius.android.banker.R;
import com.vaskevicius.android.banker.data.models.Date;
import com.vaskevicius.android.banker.data.models.Summary;
import com.vaskevicius.android.banker.data.models.Transaction;
import com.vaskevicius.android.banker.data.models.TransactionType;
import com.vaskevicius.android.banker.databinding.LiTransactionBinding;
import com.vaskevicius.android.banker.utils.DateUtils;
import com.vaskevicius.android.banker.utils.FormattingUtils;
import com.vaskevicius.android.banker.utils.SummaryUtils;
import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

  private List<Transaction> transactions = new ArrayList<>();
  private List<Object> transactionsWithDates = new ArrayList<>();
  private Context context;

  public TransactionAdapter(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new TransactionViewHolder(
        LiTransactionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
    final Object object = transactionsWithDates.get(position);

    if (object instanceof Transaction) {
      holder.bindTransaction((Transaction) object);
    } else {
      holder.bindDate((Date) object);
    }

    setFadeAnimation(holder.itemView);
  }

  @Override
  public int getItemCount() {
    return transactionsWithDates != null ? transactionsWithDates.size() : 0;
  }

  public void setItems(List<Transaction> transactions) {
    this.transactionsWithDates = getTransactionsWithDates(transactions);
    this.transactions = transactions;
    notifyDataSetChanged();
  }

  private void setFadeAnimation(View view) {
    AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
    anim.setDuration(2000);
    view.startAnimation(anim);
  }

  //ViewHolder
  public class TransactionViewHolder extends RecyclerView.ViewHolder implements
      View.OnClickListener {

    LiTransactionBinding binding;
    LinearLayout transactionInfo;
    LinearLayout dividerLayout;

    public TransactionViewHolder(LiTransactionBinding binding) {
      super(binding.getRoot());
      this.binding = binding;

      transactionInfo = binding.transactionInfo;
      dividerLayout = binding.dividerLayout;
      transactionInfo.setOnClickListener(this);
    }

    public void bindTransaction(Transaction transaction) {
      transactionInfo.setVisibility(View.VISIBLE);
      dividerLayout.setVisibility(View.GONE);

      binding.name.setText(transaction.getCounterPartyName());
      binding.description.setText(FormattingUtils.addLineBreaks(transaction.getDescription(), 19));
      binding.amount
          .setText(FormattingUtils.getFormattedAmount(Double.parseDouble(transaction.getAmount())));
      binding.counterPartyAccount
          .setText(FormattingUtils.getFormattedAccountNumber(transaction.getCounterPartyAccount()));
      binding.paymentDate.setText(FormattingUtils
          .getFormattedPaymentDate(DateUtils.getFormattedDate(transaction.getDate())));
      binding.personalImage.setRandomBackgroundColor(25, 68);
      binding.personalImage.setName(transaction.getCounterPartyName());

      switch (transaction.getType()) {
        case TransactionType.TYPE_DEBIT: {
          binding.amount.setTextColor(Color.GRAY);
          break;
        }

        case TransactionType.TYPE_CREDIT: {
          binding.amount.setTextColor(context.getColor(R.color.colorPrimaryDark));
          break;
        }
      }
    }

    public void bindDate(Date date) {
      //instance of "data.model.Date"
      Summary summary = SummaryUtils.getDaySummary(transactions, date.getDate());

      dividerLayout.setVisibility(View.VISIBLE);
      transactionInfo.setVisibility(View.GONE);

      binding.date.setText(DateUtils.getFormattedDate(date.getDateObject()));
      binding.spent.setText(FormattingUtils.getFormattedAmount(summary.getAmount().doubleValue()));
    }

    @Override
    public void onClick(View v) {
      LinearLayout expandableLayout = binding.expandableLayout;

      expandableLayout
          .setVisibility(expandableLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

      binding.name.setTypeface(ResourcesCompat.getFont(context,
          expandableLayout.getVisibility() == View.GONE ? R.font.opensans_semibold : R.font.opensans_bold));

      binding.personalImage
          .setVisibility(expandableLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }
  }
}