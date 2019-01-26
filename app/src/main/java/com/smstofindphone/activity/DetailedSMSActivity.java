package com.smstofindphone.activity;

import static com.smstofindphone.activity.MainMessageActivity.SMS_ROW_DATA;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.smstofindphone.R;
import com.smstofindphone.adapter.SmsDataArrayAdapter;
import com.smstofindphone.model.SmsData;
import com.smstofindphone.model.SmsRow;

public class DetailedSMSActivity extends ListActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detailed_sms);

    SmsRow smsRow = (SmsRow) getIntent().getSerializableExtra(SMS_ROW_DATA);
    setListAdapter(
        new SmsDataArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,
            smsRow.getMessageList()));

  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    SmsData item = (SmsData) getListAdapter().getItem(position);
    Toast.makeText(this, item.getBody() + " selected", Toast.LENGTH_LONG).show();

  }
}
