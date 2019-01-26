package com.smstofindphone.activity;

import static android.Manifest.permission.ACCESS_NOTIFICATION_POLICY;
import static android.Manifest.permission.MODIFY_AUDIO_SETTINGS;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import com.smstofindphone.helper.PermissionsHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PermissionsActivity extends Activity {

  private static final Logger log = Logger.getLogger(PermissionsActivity.class.getSimpleName());
  private static final int PERMISSION_ALL = 0;
  private Handler handler;
  private Runnable runnable;

  private String[] PERMISSIONS = {
      RECEIVE_SMS,
      READ_SMS,
      MODIFY_AUDIO_SETTINGS,
      ACCESS_NOTIFICATION_POLICY
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    handler = new Handler();
    runnable = new Runnable() {
      @Override
      public void run() {
        log.info("Permissions granted, starting activity");
        startActivity(new Intent(PermissionsActivity.this, MainMessageActivity.class));
        finish();
      }
    };
    if (!PermissionsHelper.hasPermissions(this, PERMISSIONS)) {
      ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
    } else {
      handler.postDelayed(runnable, 100);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {

    int index = 0;
    Map<String, Integer> PermissionsMap = new HashMap<String, Integer>();
    for (String permission : permissions) {
      PermissionsMap.put(permission, grantResults[index]);
      index++;
    }

    if ((PermissionsMap.get(RECEIVE_SMS) != 0)
        || PermissionsMap.get(READ_SMS) != 0 || PermissionsMap.get(MODIFY_AUDIO_SETTINGS) != 0
        || PermissionsMap.get(ACCESS_NOTIFICATION_POLICY) != 0) {
      Toast.makeText(this, "Audio and SMS permissions are a must", Toast.LENGTH_SHORT).show();
      finish();
    } else {
      handler.postDelayed(runnable, 100);
    }
  }


}
