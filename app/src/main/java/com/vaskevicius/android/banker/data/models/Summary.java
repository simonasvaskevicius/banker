package com.vaskevicius.android.banker.data.models;

import java.math.BigDecimal;
import java.util.Date;

public class Summary implements Comparable<Summary>{

  public Summary(String type, BigDecimal amount, Date date) {
    this.type = type;
    this.amount = amount;
    this.date = date;
  }

  private String type;
  private BigDecimal amount;
  private Date date;

  public String getType() {
    return type;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Date getDate() {
    return date;
  }

  @Override
  public int compareTo(Summary o) {
    return date.getMonth()- o.date.getMonth();
  }
}
