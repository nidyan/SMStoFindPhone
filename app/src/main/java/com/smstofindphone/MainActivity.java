package com.smstofindphone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.smstofindphone.helper.PermissionsHelper;
import com.smstofindphone.receiver.SMSReceiver;
import com.smstofindphone.receiver.SmsMessageProcessor;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.smstofindphone.message";
    private static final Logger log = Logger.getLogger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.info("Entering MainActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SMSReceiver.bindProcessor(new SmsMessageProcessor(MainActivity.this));
        PermissionsHelper.checkSMSPermissions(getApplicationContext(), MainActivity.this);
    }

    public void displayTextOnClick(View view) {
        log.info("Entering displayTextOnClick");
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
