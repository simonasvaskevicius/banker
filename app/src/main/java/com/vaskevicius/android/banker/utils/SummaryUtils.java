package com.vaskevicius.android.banker.utils;

import com.vaskevicius.android.banker.data.models.Date;
import com.vaskevicius.android.banker.data.models.Summary;
import com.vaskevicius.android.banker.data.models.Transaction;
import com.vaskevicius.android.banker.data.models.TransactionType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class SummaryUtils {

  //Sum of all transaction amounts
  public static double getBalance(List<Transaction> transactions) {
    BigDecimal balance = new BigDecimal("0");

    for (Transaction transaction : transactions) {
      balance = balance.add(new BigDecimal(transaction.getAmount()));
    }
    return balance.doubleValue();
  }

  public static Summary getDaySummary(List<Transaction> transactions, String dateNormalFormat) {
    List<Transaction> localTransactions;
    localTransactions = transactions;
    //I always use BigDecimal when working with money, because its more reliable with that
    BigDecimal amount = new BigDecimal("0");

    localTransactions = localTransactions.stream().filter(c -> c.getDate().equals(dateNormalFormat))
        .collect(Collectors.toList());

    for (Transaction transaction : localTransactions) {

      //If type is debit, it makes amount negative
      if (transaction.getType().equals(TransactionType.TYPE_DEBIT)) {
        amount = amount.subtract(new BigDecimal(transaction.getAmount()));
      } else {
        amount = amount.add(new BigDecimal(transaction.getAmount()));
      }
    }

    return new Summary(TransactionType.TYPE_COMBINED, amount, new Date(dateNormalFormat).getDateObject());
  }

  public static Summary getSummaryOnMonthByType(List<Transaction> transactions, java.util.Date date, String type) {
    List<Transaction> transactionList = transactions;

    double amount = transactionList.stream()
        .filter(t -> t.getConvertedDate().getYear() == date.getYear()
            && t.getConvertedDate().getMonth() == date.getMonth())
        .filter(t -> t.getType().equals(type))
        .mapToDouble(t -> Double.parseDouble(t.getAmount())).sum();

    return new Summary(type, type.equals(TransactionType.TYPE_DEBIT)? BigDecimal.valueOf((amount*(-1))): BigDecimal.valueOf(amount), date);
  }

  public static List<Object> getTransactionsWithDates(List<Transaction> transactionList) {
    List<Object> modifiedTransactionList = new ArrayList<>();
    List<Transaction> localTransactions = new ArrayList<>();
    localTransactions = transactionList;

    for (int position = 0; position < localTransactions.size(); position++) {
      Transaction transaction = localTransactions.get(position);
      if (position != 0) {
        //Because list comes sorted by date descending already, here we can just compare transactions day with previous transaction day.
        if (!transaction.getDate().equals(localTransactions.get(position - 1).getDate())) {
          modifiedTransactionList.add(new com.vaskevicius.android.banker.data.models.Date(transaction.getDate()));
        }
      } else {
        //if position is 0, then new empty Transaction item, only have date in it. This item is first in list
        modifiedTransactionList.add(new com.vaskevicius.android.banker.data.models.Date(transaction.getDate()));
      }
      //Adds transaction item to list
      modifiedTransactionList.add(transaction);
    }
    return modifiedTransactionList;
  }

  public static Transaction getMostTransaction(List<Transaction> transactions, java.util.Date date,
      String transactionType) {
    List<Transaction> transactionList = transactions;

    return transactionList
        .stream().filter(t -> t.getConvertedDate().getYear() == date.getYear()
            && t.getConvertedDate().getMonth() == date.getMonth())
        .filter(t -> t.getType().equals(transactionType))
        .max(Comparator.comparing(Transaction::getAmountDouble)).orElse(null);
  }
}
