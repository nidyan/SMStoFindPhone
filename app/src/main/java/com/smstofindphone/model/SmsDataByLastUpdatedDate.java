package com.smstofindphone.model;

import java.util.Comparator;

public class SmsDataByLastUpdatedDate implements Comparator<SmsData> {

  @Override
  public int compare(SmsData o1, SmsData o2) {
    return o1.getReceivedDate().compareTo(o2.getReceivedDate());
  }
}
