package com.smstofindphone.activity;

import static com.smstofindphone.activity.DisplaySmsActivity.SMS_ROW_DATA;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.smstofindphone.R;
import com.smstofindphone.adapter.DetailedSmsArrayAdapter;
import com.smstofindphone.model.SmsData;
import com.smstofindphone.model.SmsRow;

public class DetailedSMSActivity extends ListActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detailed_sms);

    Intent intent = getIntent();
    SmsRow smsRow = (SmsRow) intent.getSerializableExtra(SMS_ROW_DATA);
    setListAdapter(
        new DetailedSmsArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,
            smsRow.getMessageList()));

  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    SmsData item = (SmsData) getListAdapter().getItem(position);
    Toast.makeText(this, item.getBody() + " selected", Toast.LENGTH_LONG).show();

  }
}
