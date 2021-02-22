package com.vaskevicius.android.banker.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

  public static Date getTodaysDate() {
    return new Date(System.currentTimeMillis());
  }

  public static Date getDateFromString(String date) {
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return format1.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new Date();
  }

  //Converts standard date format to simpler more understandable date format. Ex: 2020-12-10 -> 10 December 2020
  public static String getFormattedDate(String date) {
    Date convertedDate = null;
    DateFormat wordDF;
    DateFormat numberDF = new SimpleDateFormat("yyyy-MM-dd");
    //if "date" is in the same year as this, then date formatting will change dependently.
    if (getTodaysDate().getYear() == getDateFromString(date).getYear()) {
      wordDF = new SimpleDateFormat("dd MMMM");
    } else {
      wordDF = new SimpleDateFormat("dd MMMM yyyy");
    }

    //Parsing String date to Date object to format it later.
    try {
      convertedDate = numberDF.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    if (convertedDate != null) {
      return wordDF.format(convertedDate);
    } else {
      return "";
    }
  }

  public static String getFormattedDate(Date date) {
    DateFormat wordDF;
    DateFormat numberDF = new SimpleDateFormat("yyyy-MM-dd");
    //if "date" is in the same year as this, then date formatting will change dependently.
    if (getTodaysDate().getYear() == date.getYear()) {
      wordDF = new SimpleDateFormat("dd MMMM");
    } else {
      wordDF = new SimpleDateFormat("dd MMMM yyyy");
    }

    //Parsing String date to Date object to format it later.
    if (date != null) {
      return wordDF.format(date);
    } else {
      return "";
    }
  }

}
