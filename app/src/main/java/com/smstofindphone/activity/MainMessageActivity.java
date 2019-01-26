package com.smstofindphone.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.smstofindphone.R;
import com.smstofindphone.adapter.SmsRowArrayAdapter;
import com.smstofindphone.model.SmsRow;
import com.smstofindphone.provider.SMSDataProvider;
import java.util.logging.Logger;

public class MainMessageActivity extends ListActivity {

  public static final String SMS_ROW_DATA = "activity.MainMessageActivity";
  private static final Logger log = Logger.getLogger(MainMessageActivity.class.getSimpleName());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    log.info("Entering MainMessageActivity oncreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_message);
    initialiseView();
  }


  private void initialiseView() {
    log.info("Initialising view for home Activity");
    SMSDataProvider smsDataProvider = new SMSDataProvider();
    final SmsRowArrayAdapter arrayAdapter = new SmsRowArrayAdapter(MainMessageActivity.this,
        android.R.layout.simple_list_item_1,
        smsDataProvider.readMessages(getApplicationContext(), MainMessageActivity.this));
    setListAdapter(arrayAdapter);

  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    log.info("Listening click event in ");
    SmsRow item = (SmsRow) getListAdapter().getItem(position);
    Intent intent = new Intent(this, DetailedSMSActivity.class);
    intent.putExtra(SMS_ROW_DATA, item);
    startActivity(intent);
    Toast.makeText(this, item.getSenderNumber() + " selected", Toast.LENGTH_LONG).show();

  }


}
