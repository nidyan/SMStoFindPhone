package com.smstofindphone.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.smstofindphone.R;
import com.smstofindphone.model.SMSData1;
import java.util.ArrayList;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_message);
    // Get the Intent that started this activity and extract the string
    Intent intent = getIntent();
    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

    // Capture the layout's TextView and set the string as its text
    TextView textView = findViewById(R.id.textView);
    textView.setText(message);
    List<SMSData1> smsList = new ArrayList<SMSData1>();

    readMessages();
  }

  public void readMessages() {
    Uri uri = Uri.parse("content://sms/inbox");
    Cursor c = getContentResolver().query(uri, null, null, null, null);
    startManagingCursor(c);

    // Read the sms data and store it in the list
    try {
      if (c.moveToFirst()) {
        for (int i = 0; i < c.getCount(); i++) {
          System.out.println(
              "Phone number is : " + c.getString(c.getColumnIndexOrThrow("address")).toString());
          System.out.println(c.getString(c.getColumnIndexOrThrow("body")).toString());
          c.moveToNext();
        }
      }
    } catch (Exception e) {
      System.out.println("some exception has occured");
    } finally {
      c.close();
    }

  }
}
