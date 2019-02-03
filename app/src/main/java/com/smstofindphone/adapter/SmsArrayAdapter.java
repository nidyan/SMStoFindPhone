package com.smstofindphone.adapter;

import static com.smstofindphone.helper.DateUtils.formatToYesterdayOrToday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.smstofindphone.R;
import com.smstofindphone.model.SmsRow;
import java.util.List;

public class SmsArrayAdapter extends ArrayAdapter<SmsRow> {

  // List context
  private final Context context;
  // List values
  private final List<SmsRow> smsList;

  public SmsArrayAdapter(Context context, int textViewResourceId, List<SmsRow> smsList) {
    super(context, textViewResourceId, smsList);
    this.context = context;
    this.smsList = smsList;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.sms_row, parent, false);

    TextView senderNumber = (TextView) rowView.findViewById(R.id.phoneNumber);
    TextView message = (TextView) rowView.findViewById(R.id.message_body);
    TextView date = (TextView) rowView.findViewById(R.id.messageDate);
    TextView unreadMessageCount = (TextView) rowView.findViewById(R.id.undreadMessageCount);

    SmsRow smsRow = getItem(position);

    senderNumber.setText(smsRow.getSenderNumber());
    if (smsRow.isUnreadMessage()) {
      unreadMessageCount.setText(String.valueOf(smsRow.getUnreadMessageCount()));
    }

    String messageBody = smsRow.getMessageList().get(0).getBody();
    if(messageBody.length() > 50){
      message.setText(messageBody.substring(0,50));
    } else {
      message.setText(messageBody);
    }
    date.setText(formatToYesterdayOrToday(smsRow.getLastUpdatedTime()));

    return rowView;
  }

}
