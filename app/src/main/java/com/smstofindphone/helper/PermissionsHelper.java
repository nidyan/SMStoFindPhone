package com.smstofindphone.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import java.util.logging.Logger;

public class PermissionsHelper {

  private static final Logger log = Logger.getLogger(PermissionsHelper.class.getSimpleName());
  private static int PERMISSION_REQ_CODE_SUBMIT = 101;
  private static int PERMISSION_REQ_AUDIO = 102;

  public static void requestPermissions(Context context, Activity activity, int permissionCode,
      String... permissionNameList) {
    log.info("Requesting permissions");

    if (!PermissionsHelper.hasPermissions(context, permissionNameList)) {
      ActivityCompat.requestPermissions(activity, permissionNameList, permissionCode);
    }
  }

  public static boolean hasPermissions(Context context, String... allPermissionNeeded) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        && context != null && allPermissionNeeded != null) {
      for (String permission : allPermissionNeeded) {
        if (ActivityCompat.checkSelfPermission(context, permission)
            != PackageManager.PERMISSION_GRANTED) {
          return false;
        }
      }
    }
    return true;
  }
}
