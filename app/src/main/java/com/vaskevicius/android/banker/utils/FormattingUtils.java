package com.vaskevicius.android.banker.utils;

import com.vaskevicius.android.banker.data.models.Transaction;
import io.reactivex.exceptions.UndeliverableException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public final class FormattingUtils {

  public static String getEuroSymbol() {
    return "\u20ac";
  }

  public static String getFormattedAccountNumber(String accountNumber) {
    return String.format("Account number:%n%1$s", accountNumber);
  }

  public static String getFormattedTransactionType(String type) {
    return String.format("Type: %s", StringUtils.capitalize(type));
  }

  public static String getFormattedItemPlusPrice(String first, String second) {
    return addLineBreaks(String.format("%s (%s)", first, second), 20);
  }

  public static String getFormattedAmount(double amount) {
    return String.format("%.2f%2$s", amount, getEuroSymbol());
  }

  public static String getFormattedAmount(String amount) {
    return String.format("%.2f%2$s", Double.parseDouble(amount), getEuroSymbol());
  }

  public static String getFormattedPaymentDate(String paymentDate) {
    return String.format("Date: %s", paymentDate);
  }

  public static String getFormattedCVV(String CVV) {
    return String.format("CVV %s", CVV);
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

  public static String addLineBreaks(String input, int maxLineLength) {
    StringTokenizer tok = new StringTokenizer(input, " ");
    StringBuilder output = new StringBuilder(input.length());
    int lineLen = 0;
    while (tok.hasMoreTokens()) {
      String word = tok.nextToken() + " ";

      if (lineLen + word.length() > maxLineLength) {
        output.append("\n");
        lineLen = 0;
      }
      output.append(word);
      lineLen += word.length();
    }
    return output.toString();
  }


}
