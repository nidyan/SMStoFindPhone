package com.smstofindphone.helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.logging.Logger;

public class PermissionsHelper {

    private static final Logger log = Logger.getLogger(PermissionsHelper.class.getSimpleName());

    public static void checkSMSPermissions(Context context, Activity activity) {
        log.info("Checking sms permissions");
        int SMS_PERMISSION_REQ_CODE_SUBMIT = 1234;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECEIVE_SMS},
                    SMS_PERMISSION_REQ_CODE_SUBMIT);
        }
    }
}
