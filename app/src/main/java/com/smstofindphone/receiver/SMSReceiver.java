package com.smstofindphone.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.logging.Logger;

public class SMSReceiver extends BroadcastReceiver {

    private static SmsMessageProcessor smsMessageProcessor;

    private static final Logger log = Logger.getLogger(SMSReceiver.class.getSimpleName());

    public static void bindProcessor(SmsMessageProcessor processor) {
        smsMessageProcessor = processor;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        log.info("Entering SMSReceiver in onReceive method");
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");

        if (pdus != null) {
            for (int i = 0; i < pdus.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                smsMessageProcessor.process(smsMessage);
            }
        }
    }
}
