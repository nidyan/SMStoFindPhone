package com.smstofindphone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.smstofindphone.R;
import com.smstofindphone.helper.DateUtils;
import com.smstofindphone.model.SmsData;
import java.util.List;

public class SmsDataArrayAdapter extends ArrayAdapter<SmsData> {

  // List context
  private final Context context;

  // List values
  private final List<SmsData> smsDataList;

  public SmsDataArrayAdapter(Context context, int textViewResourceId,
      List<SmsData> smsDataList) {
    super(context, textViewResourceId, smsDataList);
    this.context = context;
    this.smsDataList = smsDataList;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.detailed_message_data, parent, false);

    TextView date = (TextView) rowView.findViewById(R.id.singleMessageDate);
    TextView message = (TextView) rowView.findViewById(R.id.single_message_body);

    SmsData smsData = getItem(position);

    date.setText(DateUtils.getMonthTime(smsData.getReceivedDate()));

    message.setText(smsData.getBody());
    return rowView;
  }
}
