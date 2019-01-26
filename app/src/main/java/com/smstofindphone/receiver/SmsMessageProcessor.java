package com.smstofindphone.receiver;

import android.content.Context;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.logging.Logger;

public class SmsMessageProcessor implements MessageProcessor<SmsMessage> {

    private Context context;

    private static final Logger log = Logger.getLogger(SmsMessageProcessor.class.getSimpleName());

    public SmsMessageProcessor(Context context) {
        this.context = context;
    }

    @Override
    public void process(SmsMessage message) {
        log.info("Processing message");
        Toast.makeText(context, "Message: " + message.getDisplayMessageBody(), Toast.LENGTH_LONG).show();
    }
}
