package com.vaskevicius.android.banker.utils;

import com.vaskevicius.android.banker.data.models.Date;
import com.vaskevicius.android.banker.data.models.Summary;
import com.vaskevicius.android.banker.data.models.Transaction;
import com.vaskevicius.android.banker.data.models.TransactionType;
import java.math.BigDecimal;
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
    //Always use BigDecimal when working with money, because its more reliable with that
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

  public static Summary getSummaryOnMonth(List<Transaction> transactions, java.util.Date date, String type) {
    List<Transaction> transactionList = transactions;

    double spentAmount = transactionList.stream()
        .filter(t -> t.getConvertedDate().getYear() == date.getYear()
            && t.getConvertedDate().getMonth() == date.getMonth())
        .filter(t -> t.getType().equals(type))
        .mapToDouble(t -> Double.parseDouble(t.getAmount())).sum();

    return new Summary(type, type.equals(TransactionType.TYPE_DEBIT)? BigDecimal.valueOf((spentAmount*(-1))): BigDecimal.valueOf(spentAmount), date);
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
