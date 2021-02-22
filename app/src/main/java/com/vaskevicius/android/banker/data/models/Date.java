package com.vaskevicius.android.banker.data.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {


  public Date(String date) {
    this.date = date;
  }

  private String date;

  public String getDate() {
    return date;
  }

  public java.util.Date getDateObject() {
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return format1.parse(this.date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new java.util.Date();
  }
}
