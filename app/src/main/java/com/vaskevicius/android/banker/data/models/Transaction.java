package com.vaskevicius.android.banker.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Comparable<Transaction> {

  public Transaction(String id, String counterPartyName, String counterPartyAccount,
      String type, String amount, String description, String date) {
    this.id = id;
    this.counterPartyName = counterPartyName;
    this.counterPartyAccount = counterPartyAccount;
    this.type = type;
    this.amount = amount;
    this.description = description;
    this.date = date;
  }

  @Expose
  @SerializedName("id")
  private String id;

  @Expose
  @SerializedName("counterPartyName")
  private String counterPartyName;

  @Expose
  @SerializedName("counterPartyAccount")
  private String counterPartyAccount;

  @Expose
  @SerializedName("type")
  private String type;

  @Expose
  @SerializedName("amount")
  private String amount;

  @Expose
  @SerializedName("description")
  private String description;

  @Expose
  @SerializedName("date")
  private String date;

  public String getId() {
    return id;
  }

  public String getCounterPartyName() {
    return counterPartyName;
  }

  public String getCounterPartyAccount() {
    return counterPartyAccount;
  }

  public String getType() {
    return type;
  }

  public String getAmount() {
    return amount;
  }

  public Double getAmountDouble() {
    return Double.parseDouble(amount);
  }

  public String getDescription() {
    return description;
  }

  public String getDate() {
    return date;
  }

  public Date getConvertedDate() {
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return format1.parse(this.date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new Date();
  }

  public static Transaction getEmptyTransaction() {
    return new Transaction(null, null, null, null, null,null, null);
  }

  @Override
  public int compareTo(Transaction o) {
    return o.getConvertedDate().compareTo(getConvertedDate());
  }
}
