package com.smstofindphone.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.smstofindphone.R;
import com.smstofindphone.adapter.SmsArrayAdapter;
import com.smstofindphone.model.SmsRow;
import com.smstofindphone.provider.SMSDataProvider;

public class DisplaySmsActivity extends ListActivity {

  public static final String SMS_ROW_DATA = "activity.DisplaySmsActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_sms);
    initialiseView();
  }


  private void initialiseView() {
    SMSDataProvider smsDataProvider = new SMSDataProvider();
    final SmsArrayAdapter arrayAdapter = new SmsArrayAdapter(DisplaySmsActivity.this,
        android.R.layout.simple_list_item_1,
        smsDataProvider.readMessages(getApplicationContext(), DisplaySmsActivity.this));
    setListAdapter(arrayAdapter);

  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    SmsRow item = (SmsRow) getListAdapter().getItem(position);
    Intent intent = new Intent(this, DetailedSMSActivity.class);
    intent.putExtra(SMS_ROW_DATA, item);
    startActivity(intent);
    Toast.makeText(this, item.getSenderNumber() + " selected", Toast.LENGTH_LONG).show();

  }


}
