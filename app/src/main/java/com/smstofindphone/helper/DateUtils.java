package com.smstofindphone.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

  public static String formatToYesterdayOrToday(Date dateTime) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(dateTime);
    Calendar today = Calendar.getInstance();
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DATE, -1);
    DateFormat timeFormatter = new SimpleDateFormat("hh:mm a");

    if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
        && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
      return "Today " + timeFormatter.format(dateTime);
    } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR)
        && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
      return "Yesterday " + timeFormatter.format(dateTime);
    } else if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
      DateFormat dateFormat = new SimpleDateFormat("MMM  dd");
      return dateFormat.format(dateTime).toString();
    } else {
      DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
      return dateFormat.format(dateTime).toString();
    }
  }

  public static String getMonthTime(Date dateTime) {
    DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM  hh:mm a");
    return dateFormat.format(dateTime).toString();
  }

}
