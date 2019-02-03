package com.smstofindphone.helper;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import java.util.logging.Logger;

public class AudioProfile {

  private static final Logger log = Logger.getLogger(AudioProfile.class.getSimpleName());

  public enum Profiles {
    SILENT,
    NORMAL,
    VIBRATE
  }

  private static void requestMutePermissions(Context context) {
    try {
      if (Build.VERSION.SDK_INT < 23) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
      } else if (Build.VERSION.SDK_INT >= 23) {
        requestForDoNotDisturbPermissionOrSetDoNotDisturbForApi23AndUp(context);
      }
    } catch (SecurityException e) {
      log.info("Security Exception : {} " + e);
    }
  }

  private static void requestForDoNotDisturbPermissionOrSetDoNotDisturbForApi23AndUp(
      Context context) {

    NotificationManager notificationManager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
    // if user granted access else ask for permission
    if (notificationManager.isNotificationPolicyAccessGranted()) {
      AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
      audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    } else {
      // Open Setting screen to ask for permisssion
      Intent intent = new Intent(
          android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
      context.startActivity(intent);
//      startActivityForResult( intent, ON_DO_NOT_DISTURB_CALLBACK_CODE );
    }
  }

  public static void applyProfile(String message, Context context) {

    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    if (message.contains(Profiles.NORMAL.name())) {
      audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    } else if (message.contains(Profiles.VIBRATE.name())) {
      audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    } else {
      if (message.contains(Profiles.SILENT.name())) {
        requestMutePermissions(context);
      }
    }

  }

}
