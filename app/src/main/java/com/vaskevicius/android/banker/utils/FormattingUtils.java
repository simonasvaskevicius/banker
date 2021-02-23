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
