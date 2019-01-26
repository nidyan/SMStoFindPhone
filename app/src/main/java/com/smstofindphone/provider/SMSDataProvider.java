package com.smstofindphone.provider;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.smstofindphone.model.SmsData;
import com.smstofindphone.model.SmsRow;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class SMSDataProvider {

  private static final Logger log = Logger.getLogger(SMSDataProvider.class.getSimpleName());

  private Optional<SmsRow> findByNumber(List<SmsRow> smsRowList, String number) {
    return smsRowList.stream()
        .filter(smsData -> smsData.getSenderNumber().equalsIgnoreCase(number))
        .findFirst();
  }

  private static Date getDate(String date) {
    Calendar calendar = Calendar.getInstance();
    Long timestamp = Long.parseLong(date);
    calendar.setTimeInMillis(timestamp);
    Date finaldate = calendar.getTime();
    return finaldate;
  }

  public static boolean smsDateComparator(SmsData s1, SmsData s2) {
    return s1.getReceivedDate().before(s2.getReceivedDate());
  }

  public List<SmsRow> readMessages(Context context, Activity activity) {

    Uri uri = Uri.parse("content://sms/inbox");
    Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
    activity.startManagingCursor(cursor);

    List<SmsRow> inboxData = new ArrayList<>();

    try {
      // Read the sms data and store it in the list
      if (cursor.moveToFirst()) {
        for (int i = 0; i < cursor.getCount(); i++) {
          String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
          String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
          String receivedDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));
          String read = cursor.getString(cursor.getColumnIndexOrThrow("read"));
          String subject = cursor.getString(cursor.getColumnIndexOrThrow("subject"));

          SmsData smsData = SmsData.builder().body(body)
              .read(Boolean.getBoolean(read)).receivedDate(new Date(Long.valueOf(receivedDate)))
              .subject(subject).build();
          Optional<SmsRow> rowOptional = findByNumber(inboxData, number);

          SmsRow smsRow = null;

          if (rowOptional.isPresent()) {
            smsRow = rowOptional.get();
            if (smsRow.getLastUpdatedTime().before(smsData.getReceivedDate())) {
              smsRow.setLastUpdatedTime(smsData.getReceivedDate());
            }
          } else {
            smsRow = SmsRow.builder().lastUpdatedTime(smsData.getReceivedDate())
                .senderNumber(number).build();
            inboxData.add(smsRow);
          }
          if (!smsData.isRead()) {
            smsRow.setUnreadMessage(true);
            smsRow.increaseUnreadMessageCount();
          }
          smsRow.getMessageList().add(smsData);
          cursor.moveToNext();
        }
      }

    } catch (Exception e) {
      log.info("Something went wrong in the data provider");
      e.printStackTrace();
    }
    return inboxData;
  }
}
