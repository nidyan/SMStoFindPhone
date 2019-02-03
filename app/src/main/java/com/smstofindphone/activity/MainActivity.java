package com.smstofindphone.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.smstofindphone.R;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE = "com.smstofindphone.message";
  private static final Logger log = Logger.getLogger(MainActivity.class.getSimpleName());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    log.info("Entering MainActivity onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void displayTextOnClick(View view) {
    log.info("Entering displayTextOnClick");
    Intent intent = new Intent(this, DisplayMessageActivity.class);

    EditText editText = (EditText) findViewById(R.id.editText);
    String message = editText.getText().toString();
    intent.putExtra(EXTRA_MESSAGE, message);
    startActivity(intent);
  }

  public void showMessages(View view) {
    log.info("Entering showMessages");
    Intent intent = new Intent(this, DisplaySmsActivity.class);
    startActivity(intent);
  }

  public void showProfiles(View view) {
    log.info("Entering show profiles");
    Toast.makeText(MainActivity.this, "Permissions granted", Toast.LENGTH_SHORT).show();
    // Get the audio manager instance
    AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//    Toast.makeText(MainActivity.this, "Silented Ringer Mode", Toast.LENGTH_SHORT).show();
    System.out.println("ringermode is : " + String.valueOf(audioManager.getRingerMode()));

  }
}
